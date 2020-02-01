package com.seventh.icecastle.bean;

public class CheckDataResult {
    private Boolean result;  //校验成功还是事变
    private String msg;   //校验失败给出错误信息，错误信息
    private String code;  //错误代码ResponseCode

    public CheckDataResult(){
        result = true;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
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
