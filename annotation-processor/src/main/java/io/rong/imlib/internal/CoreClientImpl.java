package io.rong.imlib.internal;

import io.rong.imlib.CoreClient;
import io.rong.imlib.callback.*;
import io.rong.imlib.enums.ErrorCode;
import io.rong.imlib.internal.annotation.notnull.ReturnIfNull;

public class CoreClientImpl implements CoreClient {
    private static final String TAG = "CoreClientImpl";

    private static class SingletonHolder {
        static CoreClient sInstance = new CoreClientImpl();
    }

    public static CoreClient getInstance() {
        return SingletonHolder.sInstance;
    }

    @Override
    public void sendMessage(
            @ReturnIfNull(ErrorCode.InvalidContent)
            String content,
            IData0Callback callback
    ) {
        System.out.println(TAG + " sendMessage");
    }

    @Override
    public void sendMediaMessage(
            @ReturnIfNull(ErrorCode.InvalidLocalPath)
            String localPath,
            IData1Callback<String> callback
    ) {
        if (callback != null) {
            callback.onSuccess("1234");
        }
    }

    @Override
    public void joinChatroom(
            @ReturnIfNull(ErrorCode.InvalidRoomId)
            String roomId,
            IData2Callback<String, Integer> callback
    ) {
        System.out.println(TAG + " joinChatroom");
    }

    @Override
    public void joinExistChatroom(
            @ReturnIfNull(ErrorCode.InvalidRoomId)
            String roomId,
            IData3Callback<String, Integer, String> callback
    ) {
        System.out.println(TAG + " joinExistChatroom");
    }

    @Override
    public void syncData(
            @ReturnIfNull(ErrorCode.InvalidRoomId)
            String data
    ) {

    }
}
