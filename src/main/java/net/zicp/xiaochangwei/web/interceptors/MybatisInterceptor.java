package net.zicp.xiaochangwei.web.interceptors;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.zicp.xiaochangwei.web.multipeDataSource.DataSourceSwith;
import net.zicp.xiaochangwei.web.utils.DateUtils;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年6月23日 上午9:04:47
 * 
 */
@Intercepts({
	@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class }),
	@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class }),
	@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }) })
public class MybatisInterceptor implements Interceptor, InitializingBean {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private static final int MAPPED_STATEMENT_INDEX = 0;
	private static final int PARAMETER_INDEX = 1;
	private static final String SQL = "sql";
	private static final String SQLSOURCE_STRING = "sqlSource";
	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
	private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
	private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();

	private String shardTableName;

	private String tableFormat;

	private Pattern pattern;

	@Override
	public void afterPropertiesSet() throws Exception {
		tableFormat = shardTableName + "_%s_%s";
		pattern = Pattern.compile(shardTableName);
	}

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Method method = invocation.getMethod();

		Object[] arguments = invocation.getArgs();
		MappedStatement ms = (MappedStatement) arguments[MAPPED_STATEMENT_INDEX];
		BoundSql boundSql = ms.getBoundSql(arguments[PARAMETER_INDEX]);

		log.info("对数据库执行：" + method.getName() + " 操作, sql为:	"+ boundSql.getSql());

		// 切换数据源
		if ("query".equals(method.getName())) {
			DataSourceSwith.setDataSource(DataSourceSwith.QUERY);
		} else {
			DataSourceSwith.setDataSource(DataSourceSwith.UPDATE);
		}

		// 分表
//		MappedStatement newStatement = cloneMappedStatement(ms, boundSql);
//		Date shardDate = new Date();
//
//		replacePropertyValue(boundSql, SQL, getShardSql(boundSql, shardDate));
//		replacePropertyValue(newStatement, SQLSOURCE_STRING, new ExtSqlSource(
//				boundSql));
//		arguments[MAPPED_STATEMENT_INDEX] = newStatement;

		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/**
	 * 创建分片查询Statement
	 * 
	 * @param ms
	 * @param boundSql
	 * @return
	 */
	private final MappedStatement cloneMappedStatement(MappedStatement ms,
			BoundSql boundSql) {
		Builder builder = new Builder(ms.getConfiguration(), ms.getId(),
				new ExtSqlSource(boundSql), ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
			StringBuffer keyProperties = new StringBuffer();
			for (String keyProperty : ms.getKeyProperties()) {
				keyProperties.append(keyProperty).append(",");
			}
			keyProperties.delete(keyProperties.length() - 1,
					keyProperties.length());
			builder.keyProperty(keyProperties.toString());
		}
		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		builder.resultMaps(ms.getResultMaps());
		builder.resultSetType(ms.getResultSetType());
		builder.cache(ms.getCache());
		builder.flushCacheRequired(ms.isFlushCacheRequired());
		builder.useCache(ms.isUseCache());
		return builder.build();
	}

	/**
	 * 替换原SQL为分片SQL
	 * 
	 * @param boundSql
	 * @param yyyymm
	 */
	private String getShardSql(BoundSql boundSql, Date yearMonth) {
		return getShardSql(boundSql.getSql(), yearMonth);
	}

	@SuppressWarnings("deprecation")
	private String getShardSql(String sql, Date yearMonth) {
		String executeSql = sql;
		Matcher matcher = pattern.matcher(executeSql);

		String date = yearMonth != null ? DateUtils.dateToString(
				DateUtils.YYYYMM, yearMonth) : DateUtils.dateToString(
				DateUtils.YYYYMM, new Date());
		executeSql = matcher.replaceAll(String.format(tableFormat, date,
				yearMonth.getDate()));
		return executeSql;
	}

	/**
	 * 替换参数的值
	 * 
	 * @param object
	 * @param propertyName
	 * @param value
	 */
	private void replacePropertyValue(Object object, String propertyName,
			Object value) {
		MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY,
				DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY)
				.setValue(propertyName, value);
	}

	private static class ExtSqlSource implements SqlSource {
		BoundSql boundSql;

		protected ExtSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}

		@Override
		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}

	}

	@Override
	public void setProperties(Properties properties) {

	}

	public String getShardTableName() {
		return shardTableName;
	}

	public void setShardTableName(String shardTableName) {
		this.shardTableName = shardTableName;
	}

	public String getTableFormat() {
		return tableFormat;
	}

	public void setTableFormat(String tableFormat) {
		this.tableFormat = tableFormat;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}
}
