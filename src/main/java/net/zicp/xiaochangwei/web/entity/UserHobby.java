package net.zicp.xiaochangwei.web.entity;

import java.io.Serializable;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年7月13日 下午12:43:46
 * 
 */
public class UserHobby implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long uhId;

	private Long userId;

	private Long hId;

	public Long getUhId() {
		return uhId;
	}

	public void setUhId(Long uhId) {
		this.uhId = uhId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long gethId() {
		return hId;
	}

	public void sethId(Long hId) {
		this.hId = hId;
	}

}
