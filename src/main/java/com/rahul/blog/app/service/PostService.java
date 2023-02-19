package com.rahul.blog.app.service;

import java.util.List;

import com.rahul.blog.app.entity.Post;
import com.rahul.blog.app.payload.PostDto;
import com.rahul.blog.app.payload.PostResponce;

public interface PostService {

	
	//Create User
	
	PostDto createPost(PostDto post,Integer userId,Integer categoryId);
	
	PostDto updatePost(Integer id ,PostDto pord);
	PostDto getPostById(Integer id);
	PostResponce getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	void deletePost(Integer id);
	
	List<PostDto> getAllPostByCategory(Integer CategoryId);
	List<PostDto> getAllPostByuser(Integer userId);
	
	//search posts
	
	List<PostDto> searchPost(String keyword);
	
}
