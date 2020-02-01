package com.seventh.icecastle.bean;

/**
 * @author hubt11585
 */
public class ServerResponse<T> {
    private String msg;   //错误信息
    private String code;  //错误代码ResponseCode
    private Boolean result;  //成功or失败
    private T data;   //具体数据

    public ServerResponse(){
        result = true;
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

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
