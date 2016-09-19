package net.zicp.xiaochangwei.web.dao;

import java.util.List;

import net.zicp.xiaochangwei.web.entity.FeedBackEntity;

import org.springframework.stereotype.Repository;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年6月22日 下午3:22:39
 * 
 */
@Repository
public interface FeedBackDao {
	public void save(FeedBackEntity feedBackEntity);

	public void markAsDealed(Long fid);

	public List<FeedBackEntity> get(FeedBackEntity feedBackEntity);
	
	public FeedBackEntity getbyName(String uname);
}
