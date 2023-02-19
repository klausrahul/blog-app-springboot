package com.rahul.blog.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahul.blog.app.entity.Comment;
import com.rahul.blog.app.entity.Post;
import com.rahul.blog.app.entity.User;

public interface CommentsRepo extends JpaRepository<Comment, Integer>{
	
	
	/*
	 * List<Comment> findByUser(User user); List<Comment> findByPost(Post post);
	 */

}
