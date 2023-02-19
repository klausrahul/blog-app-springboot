package com.rahul.blog.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahul.blog.app.entity.User;

public interface UserRepo extends JpaRepository<User,Integer>{

	Optional<User> findByEmail(String email);
}
