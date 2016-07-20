package net.zicp.xiaochangwei.web.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import net.zicp.xiaochangwei.web.entity.City;
import net.zicp.xiaochangwei.web.service.CityService;
import net.zicp.xiaochangwei.web.utils.Constant;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年7月14日 上午10:59:29
 * 
 */
@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private RedisTemplate<String, String> redis;

	@Override
	public List<City> getSubCity(Long cId) {
		City city = JSON.parseObject(redis.opsForValue().get(Constant.CITY + cId), City.class);
		return city.getChildren();
	}

	@Override
	public City getParentCity(Long cId) {
		City city = JSON.parseObject(redis.opsForValue().get(Constant.CITY + cId), City.class);
		if(city.getParentId() != null){
			City parentCity = JSON.parseObject(redis.opsForValue().get(Constant.CITY + city.getParentId()), City.class);
			return parentCity;
		}
		return null;
	}

	@Override
	public List<City> getParentCitys(Long cId) {
		List<City> pCitys = new LinkedList<City>();
		City city = JSON.parseObject(redis.opsForValue().get(Constant.CITY + cId), City.class);
		if(city.getParentId() != null){
			City pc1 = JSON.parseObject(redis.opsForValue().get(Constant.CITY + city.getParentId()), City.class);
			if(pc1!=null){
				City pc2 = JSON.parseObject(redis.opsForValue().get(Constant.CITY + pc1.getParentId()), City.class);
				if(pc2!=null){
					pCitys.add(0, pc2);
					pCitys.add(1, pc1);
				}
			}
		}
		return pCitys;
	}

	@Override
	public List<City> getRootCity() {
		List<City> rootCity = new LinkedList<City>();
		rootCity = JSON.parseArray(redis.opsForValue().get(Constant.CITY_ROOT), City.class);
		return rootCity;
	}

	@Override
	public City getCity(Long cId) {
		City city = JSON.parseObject(redis.opsForValue().get(Constant.CITY + cId), City.class);
		return city;
	}

}
