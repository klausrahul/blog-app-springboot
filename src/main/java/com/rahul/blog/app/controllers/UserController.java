package com.rahul.blog.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahul.blog.app.payload.ApiResponce;
import com.rahul.blog.app.payload.UserDto;
import com.rahul.blog.app.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	
	//post
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto dto){
		UserDto user=service.createUser(dto);
		return new ResponseEntity<UserDto>(user, HttpStatus.CREATED);
	}
	//put
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable(name ="id" ) int id,@Valid @RequestBody UserDto dto){
		UserDto user=service.updateuser(id, dto);
		return new ResponseEntity<UserDto>(user, HttpStatus.OK);
	}
	
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> findAll(){
		List<UserDto> user=service.getAllUsers();
		return new ResponseEntity<List<UserDto>>(user, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> findById(@PathVariable(name ="id" ) int id){
		UserDto user=service.getUserById(id);
		return new ResponseEntity<UserDto>(user, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponce> deleteUser(@PathVariable(name ="id" ) int id){
			this.service.deleteUser(id);
		//return ResponseEntity.ok(Map.of("Message","user Deleted Successfully"));
			
			return new ResponseEntity<ApiResponce>(new ApiResponce("USer deleted Successfully", true), HttpStatus.OK);
	}
	

}
