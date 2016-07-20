package net.zicp.xiaochangwei.web.pipeline;

import java.util.Map;

/**
 * @author 肖昌伟 E-mail:changwei.xiao@56qq.com
 * @version 创建时间：2016年6月29日 上午8:36:52
 * 
 */
public interface Pipeline {
	public void runPipeline(Map<String, Object> params);
}
