package io.rong.imlib.internal.annotation.notnull;

import javassist.*;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.ParameterAnnotationsAttribute;
import javassist.bytecode.annotation.Annotation;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ReturnIfNullProcessor {
    private static String outputPath = null;
    private static final String annotationPath = "io.rong.imlib.internal.annotation.notnull.ReturnIfNull";
    private static final String[] callbackNameArray =
            {
                    "io/rong/imlib/callback/ICallback",
                    "io/rong/imlib/callback/IData0Callback",
                    "io/rong/imlib/callback/IData1Callback",
                    "io/rong/imlib/callback/IData2Callback",
                    "io/rong/imlib/callback/IData3Callback"
            };

    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException {
        System.out.println("ReturnIfNullProcessor main");

        if (args.length < 2) {
            System.out.println("Usage: ReturnIfNullProcessor <inputDir> <outputDir>");
            return;
        }
        String inputPath = args[0];
        outputPath = args[1];

        File inputDir = new File(inputPath);
        File outputDir = new File(outputPath);

        processClasses(inputDir, outputDir);
    }

    private static void processClasses(File inputDir, File outputDir) throws NotFoundException, CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        pool.appendClassPath(inputDir.getAbsolutePath());
        System.out.println("ReturnIfNullProcessor processClasses");

        // 递归处理目录下的所有文件
        processDirectory(pool, inputDir, outputDir, inputDir);
    }

    private static void processDirectory(ClassPool pool, File dir, File outputDir, File baseDir) throws NotFoundException, CannotCompileException, IOException {
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) {
                // 递归处理子目录
                processDirectory(pool, file, outputDir, baseDir);
            } else if (file.isFile() && file.getName().endsWith(".class")) {
                // 处理 .class 文件
                String classFilePath = file.getAbsolutePath();
                String className = getClassName(baseDir, classFilePath);

                System.out.println("Processing class: " + className);

                CtClass cc = pool.get(className);

                processCtClass(cc, outputDir, baseDir, file);

            }
        }
    }

    private static void processCtClass(CtClass cc, File outputDir, File baseDir, File file) throws CannotCompileException, IOException {
        // 处理方法及其注解
        for (CtMethod method : cc.getDeclaredMethods()) {
            MethodInfo methodInfo = method.getMethodInfo();

            String signature = method.getSignature();
            boolean hasCallback = hasCallbackInMethod(signature);

            ParameterAnnotationsAttribute paramAttr = (ParameterAnnotationsAttribute) methodInfo.getAttribute(ParameterAnnotationsAttribute.visibleTag);

            if (paramAttr != null) {
                Annotation[][] annotations = paramAttr.getAnnotations();
                for (int i = 0; i < annotations.length; i++) {
                    for (Annotation annotation : annotations[i]) {
                        if (annotation.getTypeName().equals(annotationPath)) {
                            String valueMemberName = "value";
                            Object valueObj = annotation.getMemberValue(valueMemberName);
                            String errorCodeString = valueObj.toString();
                            System.out.println("Adding null check for method: " + method.getName() + " parameter index: " + i + " value : " + errorCodeString);
                            String newCode = getParamCheckCode(method, i, hasCallback, errorCodeString);
                            method.insertBefore(newCode);
                        }
                    }
                }
            }
        }

        writeToClass(cc, outputDir, baseDir, file);
    }


    private static void writeToClass(CtClass cc, File outputDir, File baseDir, File file) throws CannotCompileException, IOException {
        // 计算相对路径并写入输出目录
        String relativePath = getRelativePath(baseDir, file);
        System.out.println("getRelativePath is: " + relativePath);
        File outputFile = new File(outputDir, relativePath);
        System.out.println("outputFile is: " + outputFile.getAbsolutePath());
        if (!outputFile.getParentFile().exists()) {
            boolean isMk = outputFile.getParentFile().mkdirs();
            System.out.println("outputFile.getParentFile() : " + isMk);
        }

        cc.writeFile(outputPath);
        System.out.println("Modified file written to: " + outputPath);
    }

    private static String getClassName(File baseDir, String classFilePath) {
        String classFilePathRelative = classFilePath.substring(baseDir.getAbsolutePath().length() + 1);
        classFilePathRelative = classFilePathRelative.replace(File.separatorChar, '.');
        return classFilePathRelative.substring(0, classFilePathRelative.length() - ".class".length());
    }


    private static String getRelativePath(File baseDir, File file) {
        // 获取相对于 baseDir 的相对路径
        // 保证相对路径只包含一次 baseDir
        return file.getAbsolutePath().substring(baseDir.getAbsolutePath().length() + 1);
    }

    private static String getParamCheckCode(CtMethod method, int paramIndex, boolean hasCallback, String errorCodeString) {
        String code = "{ if ($" + (paramIndex + 1) + " == null) return; }";
        if (hasCallback) {
            code = "{ if ($" + (paramIndex + 1) + " == null) { if (callback != null) { callback.onError( " + errorCodeString + "); return;}  }}";
        }
        System.out.println("getParamCheckCode : " + code);
        return code;
    }

    private static boolean hasCallbackInMethod(String signature) {
        System.out.println("signature : " + signature);
        for (String callbackName : callbackNameArray) {
            if (signature.contains(callbackName)) {
                return true;
            }
        }
        return false;
    }
}