package net.zicp.xiaochangwei.web.pipeline.impl;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Map;

import net.zicp.xiaochangwei.web.pipeline.Handler;
import net.zicp.xiaochangwei.web.pipeline.Pipeline;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年6月29日 上午8:38:22
 * 
 */
public class PipelineImpl implements Pipeline, InitializingBean {

	private List<Handler> handlers;

	private Handler head;

	@Override
	public void afterPropertiesSet() throws Exception {
		if (CollectionUtils.isEmpty(handlers)) {
			throw new InvalidParameterException("handlers can not be null");
		}

		Handler current = null;
		for (Handler handler : handlers) {
			if (head == null) {
				head = handler;
			}
			if (current != null) {
				current.setNextHandler(handler);
			}
			current = handler;
		}
	}

	@Override
	public void runPipeline(Map<String, Object> params) {
		head.handle(params);
	}

	public List<Handler> getHandlers() {
		return handlers;
	}

	public void setHandlers(List<Handler> handlers) {
		this.handlers = handlers;
	}

}
