package net.zicp.xiaochangwei.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import net.zicp.xiaochangwei.web.entity.Permission;
import net.zicp.xiaochangwei.web.entity.Role;
import net.zicp.xiaochangwei.web.utils.Constant;
import net.zicp.xiaochangwei.web.utils.SpringBeanUtil;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.JSON;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年9月18日 上午11:45:06
 * 
 */
public class HasPermissionTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	
	private RedisTemplate<String, String> redisTemplate;
	
	String name = null;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int doStartTag() throws JspException {
        try {
			return isPermitted();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return TagSupport.SKIP_BODY;
    }
	
	public int isPermitted() throws Exception{
		String p = getName();
		boolean show = checkPermission(p);
        if (show) {
            return TagSupport.EVAL_BODY_INCLUDE;
        } else {
            return TagSupport.SKIP_BODY;
        }
	}
	
	@SuppressWarnings("unchecked")
	public boolean checkPermission(final String permissionCode)
			throws Exception {
		redisTemplate = (RedisTemplate<String, String>) SpringBeanUtil.getBean("redisCache");
		String cachedRole = redisTemplate.opsForValue().get(Constant.ROLE+"1");
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
}
