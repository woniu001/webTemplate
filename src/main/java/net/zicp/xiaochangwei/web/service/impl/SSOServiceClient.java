package net.zicp.xiaochangwei.web.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import net.zicp.xiaochangwei.web.dao.UserDao;
import net.zicp.xiaochangwei.web.entity.User;
import net.zicp.xiaochangwei.web.utils.Constant;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.JSON;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年7月12日 下午3:41:18
 * 
 */
public class SSOServiceClient implements InitializingBean {
	
	private static final Logger LOGGER = Logger.getLogger(SSOServiceClient.class);
	
	private boolean localCacheOn = true;
	
	// 本地Session缓存
    private Map<String, User> localSessionCache = new ConcurrentHashMap<String, User>(500);
    
    private Map<String, String> localSessionCacheIdToken = new ConcurrentHashMap<String, String>(500);

    @Autowired
	private RedisTemplate<String, String> redisSessionCache;
    
    @Autowired
    private UserDao userdao;
    
	private static final int SESSION_KEEP_ALIVE_TIME = 60 * 60 * 24 * 7 * 1000;
	
	private static final String LOGIN_USER_ = "login_user_";
	
	@Override
	public void afterPropertiesSet() throws Exception {
		if(localCacheOn){
			final Thread sessionCacheClearThread = new Thread(new Runnable() {
	            @Override
	            public void run() {
	                while (true) {
	                    try {
	                        Thread.sleep(SESSION_KEEP_ALIVE_TIME);
	                    } catch (InterruptedException e) {
	                        LOGGER.error("localSessionThread Exception:", e);
	                    }
	
	                    try {
	                        final List<User> mapSessionCache = new ArrayList<User>(localSessionCache.values());
	                        Collections.sort(mapSessionCache);
	                        long expireTime = System.currentTimeMillis() - SESSION_KEEP_ALIVE_TIME;
	                        // 删除localSessionCache中的本地缓存
	                        for (int i = 0, len = mapSessionCache.size(); i < len; i++) {
	                            final User session = mapSessionCache.get(i);
	                            final String token = session.getToken();
	                            Date startTime = session.getStartTime();
	                            if (startTime.getTime() < expireTime) {
	                            	LOGGER.info("清理过期缓存,执行时间:"+new Date());
	                                localSessionCache.remove(token);
	                                localSessionCacheIdToken.remove(String.valueOf(session.getUserId()));
	                            } else {
	                                // 剩下的Session本地缓存均未过期，则直接跳出循环
	                                break;
	                            }
	                        }
	                    } catch (Exception e) {
	                        LOGGER.error("localSessionThread Exception:", e);
	                    }
	            }
	            }
	        });
			sessionCacheClearThread.setDaemon(true);
			sessionCacheClearThread.start();
		}
	}

	public User startSession(User user) {
		User userSession = userdao.userLogin(user);
		if(userSession != null){
//			UserInfo userInfo = userdao.getUserInfo(userSession.getUserId());
//			if(userInfo != null){
//				userSession.setUserInfo(userInfo);
//			}
			userSession.setToken(Constant.getUUID());
			userSession.setUserId(userSession.getUserId());
			userSession.setStartTime(new Date());
			if(localCacheOn){
				//让同一个用户上次登录信息失效
				String token= localSessionCacheIdToken.get(LOGIN_USER_ + userSession.getUserId());
				if(StringUtils.isNotEmpty(token)){
					localSessionCacheIdToken.remove(LOGIN_USER_ + userSession.getUserId());
					localSessionCache.remove(token);
				}
				localSessionCacheIdToken.put(LOGIN_USER_ + userSession.getUserId(), userSession.getToken());
				localSessionCache.put(userSession.getToken(), userSession);
			}
			
			String token = redisSessionCache.opsForValue().get(LOGIN_USER_ + userSession.getUserId());
			if(StringUtils.isNotEmpty(token)){
				redisSessionCache.delete(LOGIN_USER_ + userSession.getUserId());
				redisSessionCache.delete(token);
			}
			redisSessionCache.opsForValue().set(LOGIN_USER_ + userSession.getUserId(), userSession.getToken());
			redisSessionCache.opsForValue().set(userSession.getToken(), JSON.toJSONString(userSession));
			redisSessionCache.expire(LOGIN_USER_ + userSession.getUserId(), SESSION_KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS);
			redisSessionCache.expire(userSession.getToken(), SESSION_KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS);
		}
		return userSession;
	}
	
	public User getSessionByToken(String token){
		if(localCacheOn){
			User userSession = localSessionCache.get(token);
			if(userSession != null ){
				return userSession;
			}
		}
		
		User usersession = JSON.parseObject(redisSessionCache.opsForValue().get(token), User.class);
		if(usersession != null){
			return usersession;
		}
		
		return null;
	}
	
	public boolean tickOutUser(Long userId){
		if(localCacheOn){
			//让同一个用户上次登录信息失效
			String token= localSessionCacheIdToken.get(LOGIN_USER_ + userId);
			if(StringUtils.isNotEmpty(token)){
				localSessionCacheIdToken.remove(LOGIN_USER_ + userId);
				localSessionCache.remove(token);
			}
		}
		
		String token = redisSessionCache.opsForValue().get(LOGIN_USER_ + userId);
		if(StringUtils.isNotEmpty(token)){
			redisSessionCache.delete(LOGIN_USER_ + userId);
			redisSessionCache.delete(token);
		}
		return true;
	}
}
