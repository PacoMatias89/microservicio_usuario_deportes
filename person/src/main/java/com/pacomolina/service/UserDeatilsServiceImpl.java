package com.pacomolina.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pacomolina.entity.User;
import com.pacomolina.entity.PrincipalUser;

@Service
public class UserDeatilsServiceImpl implements UserDetailsService {
	
	@Autowired
	PersonService personService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User person = personService.getPersonByName(username).get();
		return PrincipalUser.build(person);
	}

}
