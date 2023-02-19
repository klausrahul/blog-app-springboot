package com.rahul.blog.app.service;

import java.util.List;

import com.rahul.blog.app.payload.UserDto;

public interface UserService {

	
	//Create User
	
	UserDto createUser(UserDto user);
	
	UserDto updateuser(int id ,UserDto user);
	UserDto getUserById(int id);
	List<UserDto> getAllUsers();
	void deleteUser(int id);
	
	
	UserDto registerNewUser(UserDto user);
}
