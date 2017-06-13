package com.orisonchan.schedule.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.orisonchan.schedule.cache.UserCache;

public class StartupServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		ApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(getServletContext());
		UserCache userCache = (UserCache) applicationContext.getBean("userCache");
		if (userCache != null)
			userCache.init();
		super.init();

	}

}
