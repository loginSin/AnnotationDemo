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

    /**
     * # 示例代码
     * <pre>
     *     CoreClient.getInstance().sendMediaMessage("1234", new IData1Callback<String>() {
     *             &#64Override
     *             public void onSuccess(String data) {
     *                 System.out.println("CoreClient sendMediaMessage onSuccess " + data);
     *             }
     *
     *             &#64Override
     *             public void onError(ErrorCode errCode) {
     *                 System.out.println("CoreClient sendMediaMessage onError " + errCode);
     *             }
     *         });
     * </pre>
     * @param localPath 本地路径
     * @param callback 回调
     * @since 1
     */
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
