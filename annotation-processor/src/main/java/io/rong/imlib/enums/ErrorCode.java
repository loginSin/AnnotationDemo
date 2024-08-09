package io.rong.imlib.enums;

public enum ErrorCode {
    Unknown(-100),
    Success(0),

    InvalidContent(2),

    InvalidRoomId(3),

    InvalidLocalPath(4);

    private int code;

    ErrorCode(int code) {
        this.code = code;
    }
}
