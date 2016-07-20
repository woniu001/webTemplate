package net.zicp.xiaochangwei.web.controller;

import javax.servlet.http.HttpServletRequest;

import net.zicp.xiaochangwei.web.entity.User;
import net.zicp.xiaochangwei.web.interceptors.SSOSessionFilter;

import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年7月12日 下午8:05:02
 * 
 */
public class ControllerSupporter {
	private static final ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();

	@ModelAttribute
	public void setRequest(HttpServletRequest rquest) {
		requestLocal.remove();
		requestLocal.set(rquest);
	}
	
	public Long getCurrentUserId() {
		User user = (User) requestLocal.get().getAttribute(SSOSessionFilter.USER_INFO);
        return user.getUserId();
    }

	public User getCurrentUser(){
		User user = (User) requestLocal.get().getAttribute(SSOSessionFilter.USER_INFO);
		return user;
	}
}
