package com.akpro.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import static com.akpro.security.SecurityConstants.*;
import com.akpro.security.service.UserDetailsServiceImpl;

public class JwtAuthTokenFilter extends OncePerRequestFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthTokenFilter.class);
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = getJwt(request);
			
			if(jwt!=null && tokenProvider.validate(jwt)) {
				String username = tokenProvider.getUserNameFromjwtToken(jwt);
				
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			
		} catch (Exception e) {
			LOGGER.error("Can NOT set user authentication -> message: {}", e);
		}
		
		filterChain.doFilter(request, response);
		
	}

	private String getJwt(HttpServletRequest request) {
		String authHeader = request.getHeader(HEADER_STRING);
		
		if(authHeader!=null && authHeader.startsWith(TOKEN_PREFIX))
			return authHeader.replace(TOKEN_PREFIX, StringUtils.EMPTY);
			
		return null;
	}

}
