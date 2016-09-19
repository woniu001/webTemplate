package net.zicp.xiaochangwei.web.interceptors;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.zicp.xiaochangwei.web.annotation.SelfPermission;
import net.zicp.xiaochangwei.web.common.Result;
import net.zicp.xiaochangwei.web.entity.Permission;
import net.zicp.xiaochangwei.web.entity.Role;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 对有@SelfPermission标签的方法进行拦截，模拟进行权限检查
 * 
 * @author xiaochangwei
 *
 */
public class SelfPermissionInterceptor implements HandlerInterceptor {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			SelfPermission permission = getAnnotation(handlerMethod, SelfPermission.class);
			if (permission != null) {
				try {
					return checkPermission(permission.value());
				} catch (Exception e) {
					Result result = new Result("120001", "没得权限", Result.Status.ERROR);
					response.setContentType("text/plain;charset=UTF-8");
					response.getWriter().write(JSONObject.toJSONString(result));
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}

	public boolean checkPermission(final String permissionCode)
			throws Exception {
		String cachedRole = redisTemplate.opsForValue().get("role1");
		if (cachedRole != null) {
			System.out.println("缓存中找到权限数据");
			Role jrole = JSON.parseObject(cachedRole, Role.class);
			boolean result = IterableUtils.matchesAny(jrole.getPermissions(),
					new Predicate<Permission>() {
						public boolean evaluate(Permission p) {
							return permissionCode.equals(p.getPermission());
						}
					});

			if (!result) {
				throw new Exception("没得权限");
			}
			return result;
		} else {
			System.out.println("缓存中没有找到权限数据");
			return false;
		}
	}

	private <T extends Annotation> T getAnnotation(HandlerMethod handlerMethod,
			Class<T> clazz) {
		T annotation = handlerMethod.getMethodAnnotation(clazz);
		if (annotation != null) {
			return annotation;
		}
		annotation = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), clazz);
		return annotation;
	}

}
