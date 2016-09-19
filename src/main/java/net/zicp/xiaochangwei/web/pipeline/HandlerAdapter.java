package net.zicp.xiaochangwei.web.pipeline;

import java.util.Map;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年6月29日 上午8:44:01
 * 
 */
public abstract class HandlerAdapter implements Handler {

	private Handler nextHandler;

	@Override
	public void handle(Map<String, Object> params) {
		boolean result = doHandler(params);
		if (result && getNextHandler() != null) {
			getNextHandler().handle(params);
		}
	}

	public abstract boolean doHandler(Map<String, Object> params);

	public Handler getNextHandler() {
		return nextHandler;
	}

	public void setNextHandler(Handler nextHandler) {
		this.nextHandler = nextHandler;
	}

}
