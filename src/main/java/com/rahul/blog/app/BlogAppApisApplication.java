package com.rahul.blog.app;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rahul.blog.app.entity.Role;
import com.rahul.blog.app.repo.RoleRepo;
import com.rahul.blog.app.util.AppConstants;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner{
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo repo;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}
	
	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(this.passwordEncoder.encode("abc"));
		
		try {
			Role role= new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ROLE_ADMIN");
			
			Role role1= new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("ROLE_NORMAL");
			
			List<Role> roles = List.of(role,role1);
			List<Role> result = repo.saveAll(roles);
			result.stream().forEach(r -> System.out.println(r));
		}catch (Exception e) {
			// TODO: handle exception
			
		
			
		}
	}

}
