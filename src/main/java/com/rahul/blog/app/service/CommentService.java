package com.rahul.blog.app.service;

import java.util.List;

import com.rahul.blog.app.payload.CategoryDto;
import com.rahul.blog.app.payload.CommentDTO;

public interface CommentService {

	
	  CommentDTO createComment(Integer userId,Integer postId,CommentDTO comment);
	 
	//CommentDTO createComment(Integer postId,CommentDTO comment);
	/*
	 * CommentDTO updateComment(Integer id, CommentDTO comment);
	 * 
	 * CommentDTO getCommentById(Integer id);
	 * 
	 * List<CommentDTO> getAllComments();
	 */
	void deleteComment (Integer id);
	
	/*
	 * List<CommentDTO> getCommentsByUser(Integer userId); List<CommentDTO>
	 * getCommentsByPost(Integer postId);
	 */
}
