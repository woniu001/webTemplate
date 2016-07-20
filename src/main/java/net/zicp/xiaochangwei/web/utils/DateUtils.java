package net.zicp.xiaochangwei.web.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static final String YYYYMM = "yyyyMM";

	public static final String YYYYMMDD = "yyyyMMdd";

	public static final String DATETIME = "yyyy-MM-dd HH:mm:ss";

	public static final String DATETIME2 = "yyyy-M-d H:m:s";

	public static final String DATETIME3 = "yy-M-d H:m:s";

	public static final String DASHED = "-";

	public static final String COLON = ":";

	public static final String SPACE = " ";

	/**
	 * 字符串转换为日期
	 * 
	 * @param pattern
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date stringToDate(String pattern, String date)
			throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException("日期格式错误");
		}
	}

	/**
	 * 日期转换为字符串
	 * 
	 * @param pattern
	 * @param date
	 * @return
	 */
	public static String dateToString(String pattern, Date date) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	/**
	 * 计算两个时间相差几小时
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static Double timeDiff(Date beginDate, Date endDate) {
		double hours = (double) (endDate.getTime() - beginDate.getTime()) / 3600 / 1000;
		return hours;
	}

}