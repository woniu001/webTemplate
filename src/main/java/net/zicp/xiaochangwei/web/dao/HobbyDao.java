package net.zicp.xiaochangwei.web.dao;

import java.util.List;

import net.zicp.xiaochangwei.web.entity.Hobby;
import net.zicp.xiaochangwei.web.entity.HobbyType;

import org.springframework.stereotype.Repository;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年7月13日 下午5:20:26
 * 
 */
@Repository
public interface HobbyDao {

	public List<HobbyType> getAllHobbys();
	
	public List<Hobby> getHobbyItems(Long hobbyTypeId);
}
