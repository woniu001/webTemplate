package net.zicp.xiaochangwei.web.service;

import java.util.List;

import net.zicp.xiaochangwei.web.entity.City;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年7月14日 上午10:57:14
 * 
 */
public interface CityService {

	public List<City> getSubCity(Long cId);

	public City getParentCity(Long cId);
	
	public City getCity(Long cId);
	
	public List<City> getParentCitys(Long cId);
	
	public List<City> getRootCity();

}
