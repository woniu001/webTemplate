package net.zicp.xiaochangwei.web.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年7月13日 下午12:41:25
 * 
 */
public class HobbyType implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long htId;

	private String htName;

	List<Hobby> hobbys = new LinkedList<Hobby>();

	public Long getHtId() {
		return htId;
	}

	public void setHtId(Long htId) {
		this.htId = htId;
	}

	public String getHtName() {
		return htName;
	}

	public void setHtName(String htName) {
		this.htName = htName;
	}

	public List<Hobby> getHobbys() {
		return hobbys;
	}

	public void setHobbys(List<Hobby> hobbys) {
		this.hobbys = hobbys;
	}

}
