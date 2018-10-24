package com.akpro.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akpro.bo.BaseResponse;
import com.akpro.bo.CommonRS;
import com.akpro.bo.ListRS;
import com.akpro.bo.UserBo;
import com.akpro.bo.UserDetailsBo;
import com.akpro.enums.ResponseStatusEnum;
import com.akpro.enums.UserRoleEnum;
import com.akpro.service.UserService;

@RestController
@RequestMapping("user")
public class UsersController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);
	
	
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
			LOGGER.error("Error: "+ e.getMessage());		
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Not get list : "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/signin", method=RequestMethod.POST)
	public BaseResponse<?> signIn(@RequestBody UserBo userBo) {
		try {
			userBo.setRole(UserRoleEnum.PARTNER.getUserDescription());
			String token = userService.getSignIn(userBo);
			LOGGER.info("Token: ", token);
			
			CommonRS<String> commonRS = new CommonRS<>(ResponseStatusEnum.SUCCESS.getDescription(), HttpStatus.OK.value(), "SignIn Successfully.");
			commonRS.setData(token);
			
			return commonRS;
		} catch(Exception e) {		
			LOGGER.error("Error: "+ e.getMessage());	
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "SignIn Not Successfully.");
		}
	}
	
	@RequestMapping(value="/admin/signin", method=RequestMethod.POST)
	public BaseResponse<?> adminSignIn(@RequestBody UserBo userBo) {
		try {
			userBo.setRole(UserRoleEnum.ADMIN.getUserDescription());
			String token = userService.getSignIn(userBo);
			LOGGER.info("Token: ", token);
			
			CommonRS<String> commonRS = new CommonRS<>(ResponseStatusEnum.SUCCESS.getDescription(), HttpStatus.OK.value(), "SignIn Successfully.");
			commonRS.setData(token);
			
			return commonRS;
		} catch(Exception e) {			
			LOGGER.error("Error: "+ e.getMessage());
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "SignIn Not Successfully.");
		}
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public BaseResponse<?> signup(@RequestBody UserDetailsBo userDetailsBo) {
		try {
			userDetailsBo = userService.getSignUp(userDetailsBo);
			
			return new BaseResponse<String>(ResponseStatusEnum.SUCCESS.getDescription(), HttpStatus.OK.value(), "SignUp Successfully.");
		} catch(Exception e) {			
			LOGGER.error("Error: "+ e.getMessage());
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "SignUp Not Successfully.");
		}
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public BaseResponse<?> logout(@RequestHeader(value="Authorization") String token) {
		try {
			userService.getLogout(token);
			
			return new BaseResponse<String>(ResponseStatusEnum.SUCCESS.getDescription(), HttpStatus.OK.value(), "Logout Successfully.");
		} catch(Exception e) {	
			LOGGER.error("Error: "+ e.getMessage());		
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Logout Not Successfully.");
		}
	}
	
}
