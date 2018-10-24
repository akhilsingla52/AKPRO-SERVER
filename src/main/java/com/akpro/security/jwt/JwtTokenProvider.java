package com.akpro.security.jwt;

import static com.akpro.security.SecurityConstants.SECRET;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.akpro.bean.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

	public boolean validate(String token) {
		try {
			Claims body = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token)
					.getBody();
	
			if(body!=null)
				return true;
		} catch (Exception e) {
			System.err.println(e);
		}
		
		return false;
	}

	public String getUserNameFromjwtToken(String token) {
		try {
			Claims body = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token)
					.getBody();
			
			LOGGER.info("Claims ID: "+body.getId());
			
			LOGGER.info("Claims Subject: "+body.getSubject());
	
			if(body!=null)
				return body.getSubject();
		} catch (Exception e) {
			System.err.println(e);
		}
		
		return null;
	}
	
	public String generate(Users user) {
		Claims claims = Jwts.claims()
				.setSubject(user.getUserName());
		claims.put("userId", String.valueOf(user.getId()));
		claims.put("role", user.getRole());
		
		return Jwts.builder()
			.setClaims(claims)
			.signWith(SignatureAlgorithm.HS512, SECRET)
			.compact();
	}

}
