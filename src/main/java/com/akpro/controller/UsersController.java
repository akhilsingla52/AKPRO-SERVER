package com.akpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.akpro.bo.BaseResponse;
import com.akpro.bo.UserDetailsBo;
import com.akpro.enums.ResponseStatusEnum;
import com.akpro.service.UserService;

@RestController
@RequestMapping("user")
public class UsersController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public BaseResponse<?> getUserList() {
		try {
			List<UserDetailsBo> userList = userService.getUserList();
			
			return new BaseResponse<List<UserDetailsBo>>(userList, ResponseStatusEnum.SUCCESS.getDescription(), HttpStatus.OK.value(), "Getting list of users");
		} catch(Exception e) {			
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Not get list : "+e.getMessage());
		}
	}
	
	
}
