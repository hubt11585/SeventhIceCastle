package com.seventh.icecastle.enums;

/**
 * 
 */
public enum ResponseCode {

	PARAM_ERROR("1120002", "请求参数错误");
	private String value;
	private String msg;

	public String getValue() {
		return value;
	}

	public String getMsg() {
		return msg;
	}

	ResponseCode(String value, String msg) {
        this.value = value;
		this.msg = msg;
	}
}
