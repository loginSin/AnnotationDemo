package io.rong.imlib.callback;

import io.rong.imlib.enums.ErrorCode;

/**
 * @author qi
 */
public interface ICallback {
    /**
     * 错误回调
     *
     * @param errCode 错误码
     */
    void onError(ErrorCode errCode);
}
