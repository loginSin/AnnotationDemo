package io.rong.imlib;

import io.rong.imlib.callback.*;
import io.rong.imlib.internal.CoreClientImpl;

public interface CoreClient {

    static CoreClient getInstance() {
        return CoreClientImpl.getInstance();
    }

    void sendMessage(String content, IData0Callback callback);

    void sendMediaMessage(String localPath, IData1Callback<String> callback);

    void joinChatroom(String roomId, IData2Callback<String, Integer> callback);

    void joinExistChatroom(String roomId, IData3Callback<String, Integer, String> callback);

    void syncData(String data);
}
