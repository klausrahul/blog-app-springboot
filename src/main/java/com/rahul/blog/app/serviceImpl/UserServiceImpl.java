package com.rahul.blog.app.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rahul.blog.app.entity.Role;
import com.rahul.blog.app.entity.User;
import com.rahul.blog.app.exception.ResourceNotFoundException;
import com.rahul.blog.app.payload.UserDto;
import com.rahul.blog.app.repo.RoleRepo;
import com.rahul.blog.app.repo.UserRepo;
import com.rahul.blog.app.service.UserService;
import com.rahul.blog.app.util.AppConstants;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	
	@Transactional
	@Override
	public UserDto createUser(UserDto user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User saveduser=repo.save(dtoToUser(user));
		
		return userToDto(saveduser);
	}

	@Override
	public UserDto updateuser(int id, UserDto userDto) {
		User user= repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","Id",id));
		/*
		 * user.setName(userDto.getName()); user.setEmail(userDto.getEmail());
		 * user.setAbout(userDto.getAbout()); user.setPassword(userDto.getPassword());
		 */
		
		User updateduser=repo.save(user);
		
		return userToDto(updateduser);
	}

	@Transactional
	@Override
	public UserDto getUserById(int id) {
		User user= repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found with user id " + id));
		return userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		
		List<User> userList=repo.findAll();
	
		
		List<UserDto> userDto=userList.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		
		return userDto;
	}

	@Override
	public void deleteUser(int id) {
		
		  User user=repo.findById(id).orElseThrow(() -> new
		  ResourceNotFoundException("User","Id",id)); repo.delete(user);
		 
		//repo.deleteById(id);
	}
	
	private User dtoToUser(UserDto dto) {
		User user=mapper.map(dto, User.class); 
		/*
		 * user.setId(dto.getId()); user.setName(dto.getName());
		 * user.setEmail(dto.getEmail()); user.setAbout(dto.getAbout());
		 * user.setPassword(dto.getPassword());
		 */
		return user;
	}

	
	private UserDto userToDto(User user) {
		UserDto dto=mapper.map(user, UserDto.class);
		/*
		 * dto.setId(user.getId()); dto.setName(user.getName());
		 * dto.setEmail(user.getEmail()); dto.setAbout(user.getAbout());
		 * dto.setPassword(user.getPassword());
		 */
		return dto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user=mapper.map(userDto,User.class);
		/*
		 * user.setName(userDto.getName()); user.setEmail(userDto.getEmail());
		 * user.setAbout(userDto.getAbout());
		 */
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
		//roles
		Role role=roleRepo.findById(AppConstants.NORMAL_USER).get();
		
		user.getRole().add(role);
		User saveUser = repo.save(user);
		
		return mapper.map(saveUser, UserDto.class);
		
	}
}
