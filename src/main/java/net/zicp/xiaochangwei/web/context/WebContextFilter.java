package net.zicp.xiaochangwei.web.context;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * web注册本地线程上下文 Filter
 */
public class WebContextFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		// 解决跨域问题
		httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
		httpServletResponse.setContentType("text/plain;charset=UTF-8");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
