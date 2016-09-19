package net.zicp.xiaochangwei.web.handler;

import java.util.Map;

import org.springframework.stereotype.Service;

import net.zicp.xiaochangwei.web.pipeline.HandlerAdapter;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年6月29日 上午8:48:22
 * 
 */
@Service
public class FirstHandler extends HandlerAdapter {

	@Override
	public boolean doHandler(Map<String, Object> params) {
		String value = params.get("key").toString();
		System.out.println("first:" + value);
		params.put("key", value + "_first");
		return true;
	}

}
