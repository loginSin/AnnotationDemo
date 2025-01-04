package io.rong.imlib.internal;

import io.rong.imlib.callback.*;
import io.rong.imlib.enums.ErrorCode;
import io.rong.imlib.internal.guard.annotation.InitGuard;
import io.rong.imlib.internal.guard.annotation.ParamGuard;

import java.util.concurrent.atomic.AtomicBoolean;

public class CoreClientImpl {
    private static final String TAG = "CoreClientImpl";
    private final AtomicBoolean sInitialized = new AtomicBoolean(false);

    private static class SingletonHolder {
        static CoreClientImpl sInstance = new CoreClientImpl();
    }

    public static CoreClientImpl getInstance() {
        return SingletonHolder.sInstance;
    }


    public void init(String appKey) {
        this.sInitialized.set(true);
    }

    public boolean isInit() {
        return this.sInitialized.get();
    }


    @InitGuard
    public void sendMessage(
            @ParamGuard(ErrorCode.InvalidContent)
            String content,
            @ParamGuard(ErrorCode.InvalidLocalPath)
            String localPath,
            IData0Callback callback
    ) {
        System.out.println(TAG + " sendMessage");
    }


    @InitGuard
    public void sendMediaMessage(
            @ParamGuard(ErrorCode.InvalidLocalPath)
            String localPath,
            IData1Callback<String> callback
    ) {
        if (callback != null) {
            callback.onSuccess("1234");
        }
    }

    @InitGuard
    public void joinChatroom(
            @ParamGuard(ErrorCode.InvalidRoomId)
            String roomId,
            IData2Callback<String, Integer> callback
    ) {
        System.out.println(TAG + " joinChatroom");
    }

    @InitGuard
    public void joinExistChatroom(
            @ParamGuard(ErrorCode.InvalidRoomId)
            String roomId,
            IData3Callback<String, Integer, String> callback
    ) {
        System.out.println(TAG + " joinExistChatroom");
    }

    @InitGuard
    public void syncData(
            @ParamGuard(ErrorCode.InvalidRoomId)
            String data
    ) {

    }
}
