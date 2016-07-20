package net.zicp.xiaochangwei.web.common;

import java.io.Serializable;

public class Result implements Serializable {
	private static final long serialVersionUID = 5925101851082556646L;
	/**
	 * 信息内容
	 */
	private Object content;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 错误码
	 */
	private String errorCode;
	/**
	 * 错误描述
	 */
	private String errorMsg;

	/**
	 * 状态
	 * 
	 */
	public enum Status {
		SUCCESS("OK"), ERROR("ERROR");

		private String code;

		private Status(String code) {
			this.code = code;
		}

		public String code() {
			return this.code;
		}
	}

	/**
	 * success
	 */
	public Result() {
		this.status = Status.SUCCESS.code();
	}

	/**
	 * success
	 * 
	 * @param errorCode
	 *            错误码
	 * @param errorMsg
	 *            错误描述
	 */
	public Result(String errorCode, String errorMsg) {
		this(errorCode, errorMsg, Status.SUCCESS);
	}

	/**
	 * custom
	 * 
	 * @param errorCode
	 *            错误码
	 * @param errorMsg
	 *            错误描述
	 * @param status
	 *            状态
	 */
	public Result(String errorCode, String errorMsg, Status status) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.status = status.code();
	}

	public Object getContent() {
		return content;
	}

	public Result setContent(Object content) {
		this.content = content;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public Result setStatus(String status) {
		this.status = status;
		return this;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public Result setErrorCode(String errorCode) {
		this.errorCode = errorCode;
		return this;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public Result setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
		return this;
	}
	
	public static  Result success(Object content) {
        Result result = new Result();
        return result.setContent(content);
    }
	
	public static Result error(String code, String message) {
        return new Result(code, message, Status.ERROR);
    }
	
	public static Result error(SystemCode error) {
        return error(error.getCode(), error.getMessage());
    }
}
