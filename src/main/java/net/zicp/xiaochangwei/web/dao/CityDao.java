package net.zicp.xiaochangwei.web.dao;

import java.util.List;

import net.zicp.xiaochangwei.web.entity.City;

import org.springframework.stereotype.Repository;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年7月13日 下午3:24:17
 * 
 */
@Repository
public interface CityDao {

	List<City> getAllProvince();
	
	List<City> getCityByParentId(Long parentId);
}
