package net.zicp.xiaochangwei.web.controller;

import javax.servlet.http.HttpServletResponse;

import net.zicp.xiaochangwei.web.entity.City;
import net.zicp.xiaochangwei.web.service.CityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年7月14日 上午9:59:06
 * 
 */
@Controller
@RequestMapping("city")
public class CityController extends ControllerSupporter {

	@Autowired
	private CityService cityService;

	@RequestMapping("getUserCityInfo")
	public ModelAndView getUserCityInfo(ModelAndView mv) {
		String result = "";
		Long cityId = 3L;
		for(City c : cityService.getParentCitys(cityId)){
			result += c.getName() +" / ";
		}
		result += cityService.getCity(cityId).getName();
		System.out.println(result);
		mv.addObject("cit", result);
		mv.setViewName("city");
		return mv;
	}
	
//	@RequestMapping(value="getUserCityInfo2",produces = "application/json;charset=UTF-8")
	@RequestMapping(value="getUserCityInfo2")
	@ResponseBody
	public String getUserCityInfo2(HttpServletResponse response,ModelAndView mv) {
		String result = "";
		Long cityId = 3L;
		for(City c : cityService.getParentCitys(cityId)){
			result += c.getName() +" / ";
		}
		result += cityService.getCity(cityId).getName();
		System.out.println(result);
//		mv.addObject("cit", result);
//		mv.setViewName("city");
		return result;
	}
}
