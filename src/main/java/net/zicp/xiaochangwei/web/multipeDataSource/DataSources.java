package net.zicp.xiaochangwei.web.multipeDataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年6月23日 下午2:04:01
 * 
 */
public class DataSources extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceSwith.getDataSource();
	}

}
