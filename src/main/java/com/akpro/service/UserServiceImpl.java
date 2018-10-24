package com.akpro.service;

import static com.akpro.security.SecurityConstants.TOKEN_PREFIX;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.akpro.bean.Users;
import com.akpro.bo.ListRS;
import com.akpro.bo.UserBo;
import com.akpro.bo.UserDetailsBo;
import com.akpro.enums.UserRoleEnum;
import com.akpro.repository.UsersRepository;
import com.akpro.security.jwt.JwtTokenProvider;
import com.akpro.util.DateUtils;
import com.akpro.util.UserUtils;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private UserUtils userUtils;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
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
		
		for(Users user:users)
			userDetailsBos.add(userUtils.convertUserToUserDetailsBo(user));
		
		ListRS<UserDetailsBo> listRs = new ListRS<>();
		listRs.setData(userDetailsBos);
		listRs.setCount(users.getTotalElements());
		listRs.setPageCount(users.getTotalPages());
		
		return listRs;
	}
	
	public String getSignIn(UserBo userBo) throws Exception {
		String token = null;
		
		if( ( StringUtils.isBlank(userBo.getUserName()) && StringUtils.isBlank(userBo.getUserName()) )
				|| StringUtils.isBlank(userBo.getPassword()) )
			return token;
		
		String userName = (userBo.getUserName()==null) ? "" : StringUtils.lowerCase(userBo.getUserName());
		String email = (userBo.getEmail()==null) ? "" : StringUtils.lowerCase(userBo.getUserName());
		String password = (userBo.getPassword()==null) ? "" : userBo.getPassword();
		
		Users user = usersRepository.validateSignIn(userName, email, userBo.getRole());		
		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if(user!=null && passwordEncoder.matches(password, user.getPassword()))
			token = tokenProvider.generate(user);
		
		user.setLastLogin(DateUtils.getCurrentDateAndTimeInUTCTimeZone());
		user.setToken(token);
		
		usersRepository.save(user);
		
		return token;
	}
	
	public UserDetailsBo getSignUp(UserDetailsBo userDetailsBo) throws Exception {
		String userName = (userDetailsBo.getUserName()==null) ? "" : StringUtils.lowerCase(userDetailsBo.getUserName());
		String email = (userDetailsBo.getEmail()==null) ? "" : StringUtils.lowerCase(userDetailsBo.getEmail());
		String mobile = (userDetailsBo.getMobile()==null 
				|| StringUtils.isNumericSpace(userDetailsBo.getMobile())) ? "" : userDetailsBo.getMobile();
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = (userDetailsBo.getPassword()==null) ? "" : passwordEncoder.encode(userDetailsBo.getPassword());
		
		Users user = new Users();
		user.setUserName(userName);
		user.setEmail(email);
		user.setMobile(mobile);
		user.setRole(UserRoleEnum.PARTNER.getUserDescription());
		user.setPassword(password);
		
		user = usersRepository.save(user);
		return userUtils.convertUserToUserDetailsBo(user);
	}

	public void getLogout(String token) throws Exception {
		token = token.replace(TOKEN_PREFIX, StringUtils.EMPTY);
		String username = tokenProvider.getUserNameFromjwtToken(token);
		
		Users user = usersRepository.findByUserNameOrEmail(username, username)
				.orElseThrow(() ->
					new UsernameNotFoundException("USer Not Found With -> username or email : " + username));
		user.setToken(null);
		usersRepository.save(user);
	}
}
