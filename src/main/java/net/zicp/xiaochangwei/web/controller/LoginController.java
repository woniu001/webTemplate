package net.zicp.xiaochangwei.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.zicp.xiaochangwei.web.entity.User;
import net.zicp.xiaochangwei.web.interceptors.SSOSessionFilter;
import net.zicp.xiaochangwei.web.service.impl.SSOServiceClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年7月12日 下午3:21:53
 * 
 */
@Controller
public class LoginController extends ControllerSupporter{

	@Autowired
	private SSOServiceClient ssoServiceClient;
	
	@RequestMapping(value = "login.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView login(ModelAndView mv) {
		mv.setViewName("login");
		return mv;
	}
	
	@RequestMapping(value = "login.xhtml", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doLogin(User user, ModelAndView mv,HttpSession session) {
		User userSession = ssoServiceClient.startSession(user);
		if(userSession != null){
			session.setAttribute("token", userSession.getToken());
			mv.addObject("msg", null);
			mv.setViewName("redirect:/index.html?" + SSOSessionFilter.TOKEN + "="+userSession.getToken());
		} else {
			mv.setViewName("login");
			mv.addObject("msg", "登录失败");
		}
		return mv;
	}
	
	@RequestMapping(value = "logout*", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(User user, HttpServletRequest request) {
		ssoServiceClient.tickOutUser(getCurrentUserId());
		return "redirect:/login.html";
	}
	
}
