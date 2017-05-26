package com.orisonchan.schedule.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/** 请求数据统一编码过滤器**/
public class CharacterEncodingFilter implements Filter {

	private FilterConfig config;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String encoding = config.getInitParameter("encoding");
		if(encoding != null && !"".equals(encoding)){
			//设置请求数据的编码方式
			request.setCharacterEncoding(encoding);
			//把请求和相应对象传给过滤链的下一个要调用的过滤器或者servlet
			chain.doFilter(request,response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		// 获取filter的初始化参数的值ֵ
		this.config = arg0;
	}

}