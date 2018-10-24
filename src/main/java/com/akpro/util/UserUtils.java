package com.akpro.util;

import org.springframework.stereotype.Component;

import com.akpro.bean.Users;
import com.akpro.bo.UserDetailsBo;

@Component
public class UserUtils {
	
	public UserDetailsBo convertUserToUserDetailsBo(Users user) {
		if(user==null)
			return null;
		
		UserDetailsBo userDetailsBo = new UserDetailsBo();
		userDetailsBo.setUserId(user.getId());
		userDetailsBo.setUserName(user.getUserName());
		userDetailsBo.setEmail(user.getEmail());
		userDetailsBo.setMobile(user.getMobile());
		userDetailsBo.setRole(user.getRole());
		userDetailsBo.setCreatedDate(DateUtils.getUTCDate(user.getTimeCreated(), Constants.DATE_FORMAT));
		
		return userDetailsBo;
	}

}
