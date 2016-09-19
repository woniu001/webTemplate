package net.zicp.xiaochangwei.web.controller;

import net.zicp.xiaochangwei.web.entity.User;
import net.zicp.xiaochangwei.web.entity.UserInfo;
import net.zicp.xiaochangwei.web.utils.Constant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年6月28日 上午9:16:54
 * 
 */
@Controller
@RequestMapping("/uc")
public class UserCenterController extends ControllerSupporter {

	@Autowired
	private RedisTemplate<String, String> redis;

	@RequestMapping(value = "/index*", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView login(ModelAndView mv) {
		User user = getCurrentUser();
		if(user != null){
			String userInfoJson = redis.opsForValue().get(Constant.USER_INFO + user.getUserId());
			if(userInfoJson != null){
				UserInfo userInfo = JSON.parseObject(userInfoJson, UserInfo.class);
				mv.addObject("user", userInfo);
			}
		}
		mv.setViewName("user/index");
		return mv;
	}

	// @Autowired
	// private Pipeline pipeline;
	//
	// @RequestMapping("index*")
	// public String index(){
	//
	// Map<String, Object> params = new HashMap<String, Object>();
	// params.put("key", "orgin");
	//
	// pipeline.runPipeline(params);
	//
	// return "index";
	// }
}
