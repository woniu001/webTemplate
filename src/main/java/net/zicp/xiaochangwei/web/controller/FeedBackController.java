package net.zicp.xiaochangwei.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.zicp.xiaochangwei.web.annotation.SelfPermission;
import net.zicp.xiaochangwei.web.common.Result;
import net.zicp.xiaochangwei.web.common.SystemCode;
import net.zicp.xiaochangwei.web.entity.FeedBackEntity;
import net.zicp.xiaochangwei.web.service.FeedBackService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;


/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年6月22日 下午2:51:47
 * 意见反馈
 */

@RestController
@RequestMapping("app/feedBack")
public class FeedBackController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FeedBackService feedBackService;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@RequestMapping(value = "save", method = { RequestMethod.GET, RequestMethod.POST })
	public Result save(@RequestParam("uname") String uname, @RequestParam("comment") String comment,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			FeedBackEntity entity = new FeedBackEntity();
			entity.setUname(uname);
			entity.setComment(comment);
			feedBackService.save(entity);
			return Result.success("反馈成功");
		} catch (Exception e) {
			log.error("login error, {}", e);
			return Result.error(SystemCode.OPERATION_FAILD);
		}
	}
	
	@RequestMapping(value = "save2", method = { RequestMethod.GET, RequestMethod.POST })
	public Result save2(@RequestParam("uname") String uname, @RequestParam("comment") String comment,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			FeedBackEntity entity = new FeedBackEntity();
			entity.setUname(uname);
			entity.setComment(comment);
			feedBackService.save(entity);
			return Result.success("反馈成功");
		} catch (Exception e) {
			log.error("login error, {}", e);
			return Result.error(SystemCode.OPERATION_FAILD);
		}
	}
	
	@RequestMapping(value = "markAsDealed", method = { RequestMethod.GET, RequestMethod.POST })
	public Result markAsDealed(@RequestParam("fid") Long fid) {
		try {
			feedBackService.markAsDealed(fid);
			return Result.success("操作已成功");
		} catch (Exception e) {
			log.error("login error, {}", e);
			return Result.error(SystemCode.OPERATION_FAILD);
		}
	}
	
	@RequestMapping(value = "getById", method = { RequestMethod.GET, RequestMethod.POST })
	public Result getById(@RequestParam("fid") Long fid) {
		try {
			FeedBackEntity obj = new FeedBackEntity();
			obj.setFid(fid);
			return Result.success(feedBackService.get(obj));
		} catch (Exception e) {
			log.error("login error, {}", e);
			return Result.error(SystemCode.OPERATION_FAILD);
		}
	}
	
	@RequestMapping(value = "getByUname", method = { RequestMethod.GET, RequestMethod.POST })
	@SelfPermission("permission1")
	public Result getByUname(@RequestParam("uname") String uname) {
		try {
			String value = redisTemplate.opsForValue().get(uname);
			if(value == null) {
				FeedBackEntity obj = new FeedBackEntity();
				obj.setUname(uname);
				List<FeedBackEntity> feeds = feedBackService.get(obj);
				redisTemplate.opsForValue().set(uname, JSON.toJSONString(feeds));
				return Result.success(feedBackService.get(obj));
			} else {
				System.out.println(value);
				List<FeedBackEntity> list = JSON.parseArray(value, FeedBackEntity.class);
				for(FeedBackEntity f : list){
					System.out.println(f.getComment());
				}
				System.out.println();
			}
			return Result.success("通过redis获取成功");
		} catch (Exception e) {
			log.error("login error, {}", e);
			return Result.error(SystemCode.OPERATION_FAILD);
		}
	}
	
	@RequestMapping(value = "redisTest", method = { RequestMethod.GET, RequestMethod.POST })
	public Result redisTest(@RequestParam("uname") String uname) {
		try {
			String value = redisTemplate.opsForValue().get(uname);
			if(value == null) {
				FeedBackEntity feeds = feedBackService.getbyName(uname);
				redisTemplate.opsForValue().set(uname, JSON.toJSONString(feeds));
				return Result.success("查询成功");
			} else {
				System.out.println(JSON.parseObject(value, FeedBackEntity.class));
				FeedBackEntity en = JSON.parseObject(value, FeedBackEntity.class);
				System.out.println(en.getComment());
				return Result.success("通过redis获取成功");
			}
		} catch (Exception e) {
			log.error("login error, {}", e);
			return Result.error(SystemCode.OPERATION_FAILD);
		}
	}
}
