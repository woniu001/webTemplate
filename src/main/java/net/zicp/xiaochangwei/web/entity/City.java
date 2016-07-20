package net.zicp.xiaochangwei.web.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年7月13日 下午1:30:52
 * 
 */
public class City implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long cid;

	private String pathCode;

	private String name;

	private Long parentId;

	private int sort;

	private List<City> children = new LinkedList<City>();

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public List<City> getChildren() {
		return children;
	}

	public void setChildren(List<City> children) {
		this.children = children;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getPathCode() {
		return pathCode;
	}

	public void setPathCode(String pathCode) {
		this.pathCode = pathCode;
	}

}
