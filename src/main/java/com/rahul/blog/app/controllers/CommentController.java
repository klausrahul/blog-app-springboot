package com.rahul.blog.app.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rahul.blog.app.payload.ApiResponce;
import com.rahul.blog.app.payload.CommentDTO;
import com.rahul.blog.app.payload.PostDto;
import com.rahul.blog.app.payload.PostResponce;
import com.rahul.blog.app.service.CommentService;
import com.rahul.blog.app.service.FileService;
import com.rahul.blog.app.service.PostService;
import com.rahul.blog.app.serviceImpl.CommentServiceImpl;
import com.rahul.blog.app.util.AppConstants;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;

	private Logger log = LoggerFactory.getLogger(CommentController.class);

	
	
	  @PostMapping("/user/{userId}/Post/{postId}/comment") public
	  ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO
	  dto, @PathVariable Integer userId,
	  
	  @PathVariable Integer postId) {
	  
	  
	  log.info("[CommentController] Inside createComment  {}" ,userId); CommentDTO
	  comment = commentService.createComment(userId, postId, dto);
	  
	  log.info("[CommentController] Responce from Service  {}"
	  ,comment.toString());
	  
	  return new ResponseEntity<CommentDTO>(comment, HttpStatus.CREATED); }
	 

	
	/*
	 * @PostMapping("/Post/{postId}/comment") public ResponseEntity<CommentDTO>
	 * createComment(@RequestBody CommentDTO dto,
	 * 
	 * @PathVariable Integer postId) {
	 * 
	 * log.info("[CommentController] Inside createComment  {}", postId); CommentDTO
	 * comment = commentService.createComment(postId, dto);
	 * 
	 * log.info("[CommentController] Responce from Service  {}",
	 * comment.toString());
	 * 
	 * return new ResponseEntity<CommentDTO>(comment, HttpStatus.CREATED); }
	 */
	 

	// get by user

	/*
	 * @GetMapping("/user/{userId}/comments") public
	 * ResponseEntity<List<CommentDTO>> getCommentByuser(@PathVariable Integer
	 * userId) { List<CommentDTO> comment =
	 * commentService.getCommentsByUser(userId); return new
	 * ResponseEntity<List<CommentDTO>>(comment , HttpStatus.OK); }
	 */

	// get by category

	/*
	 * @GetMapping("/post/{postId}/comments")
	 * 
	 * public ResponseEntity<List<CommentDTO>> getCommentByPost(@PathVariable
	 * Integer postId) { List<CommentDTO> comments =
	 * commentService.getCommentsByPost(postId); return new
	 * ResponseEntity<List<CommentDTO>>(comments, HttpStatus.OK); }
	 */

	// getAll
	/*
	 * @GetMapping("/comments") public ResponseEntity<List<CommentDTO>>
	 * getAllComments() { List<CommentDTO> comments =
	 * commentService.getAllComments(); return new
	 * ResponseEntity<List<CommentDTO>>(comments, HttpStatus.OK); }
	 * 
	 * @GetMapping("/comment/{id}") public ResponseEntity<CommentDTO>
	 * getCommentsById(@PathVariable Integer id) { CommentDTO comment =
	 * commentService.getCommentById(id); return new
	 * ResponseEntity<CommentDTO>(comment, HttpStatus.OK); }
	 */

	@DeleteMapping("/comment/{id}")
	public ResponseEntity<ApiResponce> deleteCommentById(@PathVariable Integer id) {
		commentService.deleteComment(id);
		ApiResponce responce = new ApiResponce();
		responce.setMessage("Succesfully Deleted..........");
		responce.setSuccess(true);
		return new ResponseEntity<ApiResponce>(responce, HttpStatus.OK);
	}

	// Update

	/*
	 * @PutMapping("/comment/{id}") public ResponseEntity<CommentDTO>
	 * updateCommentById(@RequestBody CommentDTO dto, @PathVariable Integer id) {
	 * CommentDTO updateComment=commentService.updateComment(id, dto); return new
	 * ResponseEntity<CommentDTO>(updateComment, HttpStatus.OK); }
	 * 
	 */

}
