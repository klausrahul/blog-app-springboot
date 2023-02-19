package com.rahul.blog.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.rahul.blog.app.entity.User;
import com.rahul.blog.app.exception.ResourceNotFoundException;
import com.rahul.blog.app.repo.UserRepo;

@Component
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	UserRepo repo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Loading User From Database
		
	User user=repo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User is not avaliable."));
		
		System.out.println(repo);
		return user;
	}

}
