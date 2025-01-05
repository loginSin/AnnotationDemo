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
            @ParamGuard(value = ErrorCode.InvalidContent, logTag = "sendMessage-E")
            String content,
            @ParamGuard(value = ErrorCode.InvalidLocalPath, logTag = "sendMessage-E")
            String localPath,
            IData0Callback callback
    ) {
        System.out.println(TAG + " sendMessage");
    }


    @InitGuard
    public void sendMediaMessage(
            @ParamGuard(value = ErrorCode.InvalidLocalPath, logTag = "sendMediaMessage-E")
            String localPath,
            IData1Callback<String> callback
    ) {
        if (callback != null) {
            callback.onSuccess("1234");
        }
    }

    @InitGuard
    public void joinChatroom(
            @ParamGuard(value = ErrorCode.InvalidRoomId, logTag = "joinChatroom-E")
            String roomId,
            IData2Callback<String, Integer> callback
    ) {
        System.out.println(TAG + " joinChatroom");
    }

    @InitGuard
    public void joinExistChatroom(
            @ParamGuard(value = ErrorCode.InvalidRoomId, logTag = "joinExistChatroom-E")
            String roomId,
            IData3Callback<String, Integer, String> callback
    ) {
        System.out.println(TAG + " joinExistChatroom");
    }

    @InitGuard
    public void syncData(
            @ParamGuard(value = ErrorCode.InvalidRoomId, logTag = "joinExistChatroom-E")
            String data
    ) {

    }
}
