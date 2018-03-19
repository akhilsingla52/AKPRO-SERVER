package com.akpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.akpro.bo.UserDetailsBo;
import com.akpro.service.UserService;

@RestController
@RequestMapping("user")
public class UsersController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/userList", method=RequestMethod.GET)
	public ResponseEntity<List<UserDetailsBo>> getUserList() {
		try {
			List<UserDetailsBo> userList = userService.getUserList();
			return new ResponseEntity<List<UserDetailsBo>>(userList, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
