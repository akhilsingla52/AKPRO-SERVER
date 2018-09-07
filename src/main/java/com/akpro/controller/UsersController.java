package com.akpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.akpro.bo.CommonRS;
import com.akpro.bo.UserDetailsBo;
import com.akpro.enums.ResponseStatusEnum;
import com.akpro.service.UserService;

@RestController
@RequestMapping("user")
public class UsersController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public CommonRS<?> getUserList() {
		try {
			List<UserDetailsBo> userList = userService.getUserList();
			CommonRS<List<UserDetailsBo>> commonRS = new CommonRS<>();
			commonRS.setStatus(ResponseStatusEnum.SUCCESS.getDescription());
			commonRS.setStatusCode(HttpStatus.OK.value());
			commonRS.setMessage("Getting list of users");
			commonRS.setData(userList);
			
			return commonRS;
		} catch(Exception e) {			
			return new CommonRS<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Not get list : "+e.getMessage());
		}
	}
	
	
}
