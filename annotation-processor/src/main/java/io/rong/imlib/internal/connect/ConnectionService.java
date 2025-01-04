package io.rong.imlib.internal.connect;

import io.rong.imlib.callback.IData1Callback;
import io.rong.imlib.enums.ErrorCode;
import io.rong.imlib.internal.guard.annotation.InitGuard;
import io.rong.imlib.internal.guard.annotation.ParamGuard;

public class ConnectionService {
    private static final String TAG = "ConnectionService";

    @InitGuard
    public void connect(
            @ParamGuard(ErrorCode.InvalidToken) String token,
            IData1Callback<String> callback
    ) {
        System.out.println(TAG + " connect");
    }
}
