package net.zicp.xiaochangwei.web.service.impl;

import java.util.List;
import java.util.Map;

import net.zicp.xiaochangwei.web.dao.FeedBackDao;
import net.zicp.xiaochangwei.web.entity.FeedBackEntity;
import net.zicp.xiaochangwei.web.service.FeedBackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年6月22日 下午3:22:02
 * 
 */
@Service
public class FeedBackServiceImpl implements FeedBackService {

	@Autowired
	private FeedBackDao feedBackDao;

	@Override
	@Transactional
	public void save(FeedBackEntity feedBackEntity) {
		feedBackDao.save(feedBackEntity);
	}

	@Override
	@Transactional
	public void markAsDealed(Long fid) {
		feedBackDao.markAsDealed(fid);

	}

	@Override
	public List<FeedBackEntity> get(FeedBackEntity feedBackEntity) {
		return feedBackDao.get(feedBackEntity);
	}

	@Override
	public FeedBackEntity getbyName(String uname) {
		return feedBackDao.getbyName(uname);
	}

	@Override
	public void pipeLineHandlerTest(Map<String, Object> params) {
		System.out.println("handler service called");
		String value = params.get("key").toString();
		System.out.println("service:" + value);
		params.put("key", value + "_service");
	}

}
