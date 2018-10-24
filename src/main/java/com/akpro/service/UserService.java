package com.akpro.service;

import com.akpro.bo.ListRS;
import com.akpro.bo.UserBo;
import com.akpro.bo.UserDetailsBo;

public interface UserService {

	public ListRS<UserDetailsBo> getAllUserList(Integer page, Integer size, String sortingDirection, String sortBy, String search) throws Exception;

	public String getSignIn(UserBo userBo) throws Exception;

	public UserDetailsBo getSignUp(UserDetailsBo userDetailsBo) throws Exception;

	public void getLogout(String token) throws Exception;

}
