package com.example;

import io.rong.imlib.CoreClient;
import io.rong.imlib.callback.IData1Callback;
import io.rong.imlib.enums.ErrorCode;
import io.rong.imlib.internal.CoreClientImpl;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
        testAssistant();
    }


    public static void testAssistant() throws Exception {
        CoreClient.getInstance().sendMediaMessage("1234", null);
        CoreClient.getInstance().sendMediaMessage("1234", new IData1Callback<String>() {
            @Override
            public void onSuccess(String data) {
                System.out.println("CoreClient sendMediaMessage onSuccess " + data);
            }

            @Override
            public void onError(ErrorCode errCode) {
                System.out.println("CoreClient sendMediaMessage onError " + errCode);
            }
        });

        CoreClient.getInstance().init("1234");
        CoreClient.getInstance().sendMediaMessage(null, new IData1Callback<String>() {
            @Override
            public void onSuccess(String data) {
                System.out.println("CoreClient sendMediaMessage onSuccess " + data);
            }

            @Override
            public void onError(ErrorCode errCode) {
                System.out.println("CoreClient sendMediaMessage onError " + errCode);
            }
        });
    }
}