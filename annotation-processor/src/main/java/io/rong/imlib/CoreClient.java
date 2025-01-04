package io.rong.imlib;

import io.rong.imlib.callback.*;
import io.rong.imlib.internal.CoreClientImpl;

public class CoreClient {
    private final CoreClientImpl impl = CoreClientImpl.getInstance();

    private static class SingletonHolder {
        static CoreClient sInstance = new CoreClient();
    }

    public static CoreClient getInstance() {
        return SingletonHolder.sInstance;
    }

    public void init(String appKey) {
        this.impl.init(appKey);
    }

    public void sendMessage(String content, String localPath, IData0Callback callback) {
        this.impl.sendMessage(content, localPath , callback);
    }

    public void sendMediaMessage(String localPath, IData1Callback<String> callback) {
        this.impl.sendMediaMessage(localPath, callback);
    }

    public void joinChatroom(String roomId, IData2Callback<String, Integer> callback) {
        this.impl.joinChatroom(roomId, callback);
    }

    public void joinExistChatroom(String roomId, IData3Callback<String, Integer, String> callback) {
        this.impl.joinExistChatroom(roomId, callback);
    }

    public void syncData(String data) {
        this.impl.syncData(data);
    }
}
