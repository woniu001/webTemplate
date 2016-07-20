package net.zicp.xiaochangwei.web.entity;

import java.io.Serializable;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年7月13日 下午1:24:23
 * 
 */
public class RolePermission implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long roleId;

	private Long PermissionId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getPermissionId() {
		return PermissionId;
	}

	public void setPermissionId(Long permissionId) {
		PermissionId = permissionId;
	}

}
