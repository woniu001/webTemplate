package net.zicp.xiaochangwei.web.interceptors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.zicp.xiaochangwei.web.entity.User;
import net.zicp.xiaochangwei.web.service.impl.SSOServiceClient;

import org.apache.commons.lang.StringUtils;

/**
 * @author 肖昌伟 E-mail:317409898@qq.com
 * @version 创建时间：2016年7月12日 上午9:55:47
 * 
 */
public class SSOSessionFilter implements Filter {

	public static final String TOKEN = "token";
	
	public static final String USER_INFO = "user_info";

	private SSOServiceClient ssoServiceClient;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("Content-type", "text/plain;charset=UTF-8");
        httpServletResponse.setHeader("Content-type", "application/json;charset=UTF-8");
        
		//String token = request.getParameter(TOKEN);
        String token = (String) httpServletRequest.getSession().getAttribute("token");
		if (StringUtils.isNotEmpty(token)) {
			//检查token是否合法，合法就返回token中包含的用户信息
			User userSession = ssoServiceClient.getSessionByToken(token);
			if(userSession != null){
				httpServletRequest.setAttribute(USER_INFO, userSession);
			}
		}

		chain.doFilter(httpServletRequest, httpServletResponse);
	}

	@Override
	public void destroy() {

	}

	public SSOServiceClient getSsoServiceClient() {
		return ssoServiceClient;
	}

	public void setSsoServiceClient(SSOServiceClient ssoServiceClient) {
		this.ssoServiceClient = ssoServiceClient;
	}

	
}
