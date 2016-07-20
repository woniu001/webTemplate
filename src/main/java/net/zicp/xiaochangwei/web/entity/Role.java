package net.zicp.xiaochangwei.web.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long rid;

	private String roleName;

	private String desc;

	private List<Permission> permissions = new LinkedList<Permission>();

	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

}
