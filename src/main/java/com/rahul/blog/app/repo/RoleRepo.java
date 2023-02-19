package com.rahul.blog.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahul.blog.app.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
