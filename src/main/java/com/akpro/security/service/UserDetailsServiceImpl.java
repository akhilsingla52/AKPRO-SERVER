package com.akpro.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akpro.bean.Users;
import com.akpro.repository.UsersRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user = usersRepository.findByUserNameOrEmail(username, username)
						.orElseThrow(() ->
								new UsernameNotFoundException("USer Not Found With -> username or email : " + username));
		
		return UserPrinciple.build(user);
	}

}
