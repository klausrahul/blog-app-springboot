package com.rahul.blog.app.payload;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rahul.blog.app.entity.Role;
import com.rahul.blog.app.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {

	private int id;

	@NotEmpty
	@Size(min = 4 , message = "username must be 4 char")
	private String name;
	@Email(message = "user Email is not valid")
	private String email;
	@NotEmpty
	@Size(min = 3,max = 10,message = "password must be min 3 and max 10")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	@NotEmpty
	private String about;
	
	private Set<RoleDto> role = new HashSet<>();
	
	
	
	
}
