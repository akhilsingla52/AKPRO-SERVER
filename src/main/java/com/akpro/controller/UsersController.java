package com.akpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akpro.bo.BaseResponse;
import com.akpro.bo.ListRS;
import com.akpro.bo.UserDetailsBo;
import com.akpro.enums.ResponseStatusEnum;
import com.akpro.service.UserService;

@RestController
@RequestMapping("user")
public class UsersController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/getAllUsers", method=RequestMethod.GET)
	public BaseResponse<?> getAllUserList(@RequestParam(name="page", defaultValue="1") Integer page,
									@RequestParam(name="size", defaultValue="5") Integer size,
									@RequestParam(name="sortorder", defaultValue="ASC") String sortingDirection,
									@RequestParam(name="sortby", defaultValue="id") String sortBy,
									@RequestParam(name="search", defaultValue="") String search) {
		try {
			ListRS<UserDetailsBo> listRs = userService.getAllUserList(page, size, sortingDirection, sortBy, search);
			listRs.setStatus(ResponseStatusEnum.SUCCESS.getDescription());
			listRs.setStatusCode(HttpStatus.OK.value());
			listRs.setMessage("Getting list of users");
			
			return listRs;
		} catch(Exception e) {			
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Not get list : "+e.getMessage());
		}
	}
	
	
}
