package net.zicp.xiaochangwei.web.multipeDataSource;

/**
 * @author 肖昌伟 E-mail:changwei.xiao@56qq.com
 * @version 创建时间：2016年6月23日 下午2:04:33
 * 
 */
public class DataSourceSwith {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	public static final String UPDATE = "UPDATE";
	public static final String QUERY = "QUERY";

	public static void setDataSource(String dataSource) {
		contextHolder.set(dataSource);
	}

	public static String getDataSource() {
		return (String) contextHolder.get();
	}

	public static void clearDataSource() {
		contextHolder.remove();
	}
}
