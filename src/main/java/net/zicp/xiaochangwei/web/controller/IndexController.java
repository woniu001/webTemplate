package net.zicp.xiaochangwei.web.controller;

import net.zicp.xiaochangwei.web.service.UserInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年7月13日 下午8:36:28
 * 
 */
@Controller
public class IndexController {

	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping("index*")
	public ModelAndView index(ModelAndView mv) {
		mv.addObject("users", userInfoService.getUserInfoFromCache(100));
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping("{name}*")
	public ModelAndView userIndex(@PathVariable("name") String name,ModelAndView mv){
		mv.addObject("user", userInfoService.getUserInfoFromCache(name));
		mv.setViewName("userIndex");
		return mv;
	}
}
