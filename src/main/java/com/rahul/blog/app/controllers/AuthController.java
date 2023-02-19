package com.rahul.blog.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahul.blog.app.exception.ApiException;
import com.rahul.blog.app.payload.AuthRequest;
import com.rahul.blog.app.payload.AuthResponce;
import com.rahul.blog.app.payload.UserDto;
import com.rahul.blog.app.security.JWTUtil;
import com.rahul.blog.app.service.UserService;



@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private JWTUtil util;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService service;

	
	@PostMapping("/login")
	public ResponseEntity<AuthResponce> genrateTocken(@RequestBody AuthRequest request) throws Exception {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPass()));
		} catch (RuntimeException ex) {
			throw new ApiException("inavalid username/password");
		}

		String jwt= util.generateToken(request.getUserName());
		
		return ResponseEntity.ok(new AuthResponce(jwt));
	}
	
	
	@PostMapping("/register")
	
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto dto){
		UserDto registerNewUser = service.registerNewUser(dto);
		return new ResponseEntity<UserDto>(registerNewUser, HttpStatus.CREATED);
	}

}
