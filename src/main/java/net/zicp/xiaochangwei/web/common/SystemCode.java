package net.zicp.xiaochangwei.web.common;

public enum SystemCode {

	SYSTEM_ERROR("0001", "系统内部异常"), 
	SYSTEM_404("404", "找不到页面"), 
	OPERATION_FAILD("555", "操作失败"), ;

	private String code;

	private String message;

	private SystemCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public static SystemCode getByCode(String code) {
		for (SystemCode errorCode : SystemCode.values()) {
			if (code.equals(errorCode.getCode())) {
				return errorCode;
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public SystemCode setMessage(String message) {
		this.message = message;
		return this;
	}
}
