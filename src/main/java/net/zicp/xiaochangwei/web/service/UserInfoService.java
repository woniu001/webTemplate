package net.zicp.xiaochangwei.web.service;

import java.util.List;

import net.zicp.xiaochangwei.web.entity.UserInfo;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年7月13日 下午8:49:26
 * 
 */
public interface UserInfoService {

	public List<UserInfo> getUserInfoFromCache(int number);

	UserInfo getUserInfoFromCache(String name);
	
}
