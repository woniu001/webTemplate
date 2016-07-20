package net.zicp.xiaochangwei.web.entity;

import java.io.Serializable;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年7月13日 上午9:20:59
 * 
 */
public class Hobby implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long hid;
	
	private String name;
	
	private Long type;

	public Long getHid() {
		return hid;
	}

	public void setHid(Long hid) {
		this.hid = hid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}
	
	

}
