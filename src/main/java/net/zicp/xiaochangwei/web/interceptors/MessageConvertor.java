package net.zicp.xiaochangwei.web.interceptors;

import java.nio.charset.Charset;

import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年7月18日 上午9:35:28
 * 
 */
public class MessageConvertor extends StringHttpMessageConverter {

	private static final MediaType utf8 = new MediaType("text", "plain", Charset.forName("UTF-8")); 
	
	@Override 
	protected MediaType getDefaultContentType(String charSet){
		return utf8;
	}
}
