package com.seventh.icecastle.exception;

import com.seventh.icecastle.enums.ResponseCode;

public class MyException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    String msg;
    String code;

    public MyException(String code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    /**
     *
     * @param code
     * @param msg
     * @param cause
     */
    public MyException(String code, String msg, Throwable cause) {
        super(msg, cause);
        this.msg = msg;
        this.code = code;
    }

    public MyException(ResponseCode responseCode, Throwable cause) {
        this(responseCode.getValue(), responseCode.getMsg(), cause);
    }

    public MyException(ResponseCode responseCode) {
        this(responseCode.getValue(), responseCode.getMsg());
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
