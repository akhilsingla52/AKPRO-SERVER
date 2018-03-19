package com.akpro.interceptor;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CorsFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(CorsFilter.class);
	private String remoteReferer;

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {

		LOGGER.debug("CorsFilter called");

		HttpServletResponse response = (HttpServletResponse) res;
		
		HttpServletRequest request = (HttpServletRequest) req;

		remoteReferer = request.getHeader("referer");

		LOGGER.debug("CorsFilter :: Reomte Referrer : " + remoteReferer);
		
		if (StringUtils.isNotEmpty(remoteReferer)
				&& (remoteReferer.contains("heroku.com")
						|| remoteReferer.contains("localhost"))) {
			response.setHeader("Access-Control-Allow-Origin", "*");
		} else {
			response.setHeader("Access-Control-Allow-Origin", "*");
		}
		
		response.setHeader("X-Frame-Options", "SAMEORIGIN");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers",
				"Cache-Control,Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization,x-auth-token");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("server", "unspecified");
		try {
			chain.doFilter(req, res);
		} catch (Exception e) {
			LOGGER.error("Error occured : ", e);
		}
	}

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}

}