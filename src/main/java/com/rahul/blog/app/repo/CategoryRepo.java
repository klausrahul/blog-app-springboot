package com.rahul.blog.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.rahul.blog.app.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
