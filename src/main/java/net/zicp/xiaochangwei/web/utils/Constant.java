package net.zicp.xiaochangwei.web.utils;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年7月12日 下午4:00:11
 * 
 */
public class Constant {

	public static final String USER_INFO = "USER_INFO_";
	
	public static final String CITY = "CITY_";
	
	public static final String CITY_ROOT = "CITY_ROOT";
	
	public static final String ROLE = "ROLE_";
	
	public static final String HOBBY = "HOBBY_";
	
	public static final String LOGIN_USER = "LOGIN_USER_";
	
	
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static Long getRandom() {
		SecureRandom secureRandom = new SecureRandom();
		return Math.abs(secureRandom.nextLong());
	}

	public static String getRandomString() {
		SecureRandom secureRandom = new SecureRandom();
		return String.valueOf(Math.abs(secureRandom.nextLong()));
	}
}
