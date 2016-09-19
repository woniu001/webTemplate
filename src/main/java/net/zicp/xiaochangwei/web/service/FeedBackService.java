package net.zicp.xiaochangwei.web.service;

import java.util.List;
import java.util.Map;

import net.zicp.xiaochangwei.web.entity.FeedBackEntity;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年6月22日 下午3:20:33
 * 
 */
public interface FeedBackService {
	public void save(FeedBackEntity feedBackEntity);

	public void markAsDealed(Long fid);

	public List<FeedBackEntity> get(FeedBackEntity feedBackEntity);
	public FeedBackEntity getbyName(String uname);
	
	public void pipeLineHandlerTest(Map<String, Object> params);
}
