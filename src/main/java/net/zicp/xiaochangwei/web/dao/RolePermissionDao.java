package net.zicp.xiaochangwei.web.dao;

import java.util.List;

import net.zicp.xiaochangwei.web.entity.Permission;
import net.zicp.xiaochangwei.web.entity.Role;

import org.springframework.stereotype.Repository;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年7月13日 下午2:58:22
 * 
 */
@Repository
public interface RolePermissionDao {
	List<Role> getAllRole();
	
	List<Permission> getPermissionByRole(Long roleId);
}
