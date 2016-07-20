package net.zicp.xiaochangwei.web.entity;

import java.io.Serializable;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年7月13日 下午12:46:19
 * 
 */
public class Photos implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long pid;

	private Long userId;

	private String path;

	private int sort;

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

}
