package com.rahul.blog.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahul.blog.app.entity.Category;
import com.rahul.blog.app.entity.Post;
import com.rahul.blog.app.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category user);
	
	List<Post> findByTitleContaining(String title);
}
