# 注解用例
`annotation-processor` 是一个 Java SDK，输出一个 jar 包

`app` 是一个 Java 程序，调用 annotation-processor 的类


# annotation-processor

目标

1. annotation-processor 统一所有的 Callback
2. annotation-processor 实现 ReturnIfNull 注解，和注解生成代码，保证使用 ReturnIfNull 的注解在编译 class 时可以正常做 空保护

## 生成 jar

```shell
# 项目根路径
./gradlew jar
```
流程是

1. `annotation-processor/build/classes/java/main/` 生成正常的 class 文件
2. annotation-processor/build.gradle 执行 processNonNullAnnotations 任务修改 class
3. `annotation-processor/build/rc-modified-classes/` 存放修改后的 class 文件
4. 把修改后的 class 文件再放回 `annotation-processor/build/classes/java/main/`
5. 将 `annotation-processor/build/classes/java/main/` 路径打包为 jar

# app

app 依赖的是修改后的 class 文件生成的 jar

# 说明

为了方便修改 class ，必须强制要求所有方法的 Callback 必须是 ICallback 的子类

如果有新增的 Callback 类，必须在 ReturnIfNullProcessor.callbackNameArray 进行配置

