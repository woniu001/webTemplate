package net.zicp.xiaochangwei.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.zicp.xiaochangwei.web.entity.User;
import net.zicp.xiaochangwei.web.entity.UserInfo;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年7月12日 下午8:37:01
 * 
 */
@Repository
public interface UserDao {
	public User userLogin(User user);
	
	public UserInfo getUserInfo(Long userId);
	
	public List<UserInfo> getAllUserInfo();
}
