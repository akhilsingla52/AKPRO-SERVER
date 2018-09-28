package com.akpro.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.akpro.bean.Users;
import com.akpro.bo.ListRS;
import com.akpro.bo.UserDetailsBo;
import com.akpro.repository.UsersRepository;
import com.akpro.util.Constants;
import com.akpro.util.DateUtils;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UsersRepository usersRepository;
	
	public ListRS<UserDetailsBo> getAllUserList(Integer page, Integer size, String sortingDirection, String sortBy, String search) throws Exception {
		List<UserDetailsBo> userDetailsBos = new ArrayList<>();
		search = "%"+new String(Hex.decodeHex(search), "UTF-8")+"%";
		LOGGER.info("Search: "+search);
		
		Direction direction;
		if (sortingDirection.equals("ASC")) {
			direction = Sort.Direction.ASC;
		} else {
			direction = Sort.Direction.DESC;
		}
		
		Page<Users> users = usersRepository.findBySearch(search, PageRequest.of(page-1, size, direction, sortBy));
		
		for(Users user:users) {
			UserDetailsBo userDetailsBo = new UserDetailsBo();
			userDetailsBo.setUserId(user.getId());
			userDetailsBo.setUserName(user.getUserName());
			userDetailsBo.setEmail(user.getEmail());
			userDetailsBo.setMobile(user.getMobile());
			userDetailsBo.setRole(user.getRole());
			userDetailsBo.setCreatedDate(DateUtils.getUTCDate(user.getTimeCreated(), Constants.DATE_FORMAT));
			
			userDetailsBos.add(userDetailsBo);
		}
		
		ListRS<UserDetailsBo> listRs = new ListRS<>();
		listRs.setData(userDetailsBos);
		listRs.setCount(users.getTotalElements());
		listRs.setPageCount(users.getTotalPages());
		
		return listRs;
	}

}
