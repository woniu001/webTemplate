package net.zicp.xiaochangwei.web.service.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import net.zicp.xiaochangwei.web.entity.UserInfo;
import net.zicp.xiaochangwei.web.service.UserInfoService;
import net.zicp.xiaochangwei.web.utils.Constant;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年7月13日 下午9:04:00
 * 
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private RedisTemplate<String, String> redis;

	@Override
	public List<UserInfo> getUserInfoFromCache(int number) {
		Set<String> sets = redis.keys(Constant.USER_INFO + "*");
		Iterator<String> it = sets.iterator();
		List<UserInfo> result = new LinkedList<UserInfo>();
		int i = 0;
		while(it.hasNext() && i<number){
			String item = it.next();
			String value = redis.opsForValue().get(item);
			result.add(JSON.parseObject(value,UserInfo.class));
			i++;
		}
		return result;
	}
	
	@Override
	public UserInfo getUserInfoFromCache(String name) {
		String value = redis.opsForValue().get(Constant.USER_INFO + name);
		return JSON.parseObject(value,UserInfo.class);
	}
}
