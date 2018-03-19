package com.akpro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akpro.bean.Users;
import com.akpro.bo.UserDetailsBo;
import com.akpro.repository.UsersRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UsersRepository usersRepository;

	@Override
	public List<UserDetailsBo> getUserList() {
		List<UserDetailsBo> userList = new ArrayList<>();
		
		List<Users> users = usersRepository.findAll();
		
		for(Users user:users) {
			UserDetailsBo userDetailsBo = new UserDetailsBo();
			userDetailsBo.setUserId(user.getId());
			userDetailsBo.setUserName(user.getUserName());
			userDetailsBo.setEmail(user.getEmail());
			userDetailsBo.setMobile(user.getMobile());
			userDetailsBo.setRole(user.getRole());
			
			userList.add(userDetailsBo);
		}
		
		return userList;
	}

}
