package net.zicp.xiaochangwei.web.common;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年6月28日 上午9:24:01 常量保存
 */
public class Constants {

	public static final String USER_SESSION_KEY = "user-session-a5fb41fd-d611-4bd2-a8bb-697a1f24a75d";

	public static void main(String[] args) {
		String token = UUID.randomUUID().toString().replaceAll("-", "");
		System.out.println(token);

		SecureRandom random = new SecureRandom();
		
		System.out.println(random.hashCode());
		
		
	        int value = (int)(Math.random()*10);
	        System.out.println(value);
	       System.out.println((int)Math.pow(2, value));
	}
}
