package com.akpro.service;

import com.akpro.bo.ListRS;
import com.akpro.bo.UserDetailsBo;

public interface UserService {

	public ListRS<UserDetailsBo> getAllUserList(Integer page, Integer size, String sortingDirection, String sortBy, String search) throws Exception;

}
