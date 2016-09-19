package net.zicp.xiaochangwei.web.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年6月22日 下午3:23:55 意见反馈
 */
public class FeedBackEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long fid;
	private String uname;
	private String comment;
	private Date creatTime;
	private Date dealTime;
	
	@SuppressWarnings("unused")
	private String dealStatus;

	public Long getFid() {
		return fid;
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public String getDealStatus() {
		return dealTime == null ? "未处理" : "已处理";
	}

	public void setDealStatus(String dealStatus) {
		this.dealStatus = dealStatus;
	}
}
