package net.zicp.xiaochangwei.web.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.zicp.xiaochangwei.web.pipeline.HandlerAdapter;
import net.zicp.xiaochangwei.web.service.FeedBackService;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年6月29日 上午8:48:22
 * 
 */
@Service
public class SecondHandler extends HandlerAdapter {

	@Autowired
	private FeedBackService feedBackService;

	@Override
	public boolean doHandler(Map<String, Object> params) {
		String value = params.get("key").toString();
		System.out.println("second:" + value);
		params.put("key", value + "_second");
		feedBackService.pipeLineHandlerTest(params);
		System.out
				.println("after call service:" + params.get("key").toString());
		return true;
	}

}
