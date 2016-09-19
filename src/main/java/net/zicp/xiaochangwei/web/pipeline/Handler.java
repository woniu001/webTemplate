package net.zicp.xiaochangwei.web.pipeline;

import java.util.Map;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年6月29日 上午8:33:26
 * 
 */
public interface Handler {
	public void handle(Map<String, Object> params);

	public Handler getNextHandler();

	public void setNextHandler(Handler handler);
}
