package com.rahul.blog.app.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rahul.blog.app.entity.Comment;
import com.rahul.blog.app.entity.Post;
import com.rahul.blog.app.entity.User;
import com.rahul.blog.app.exception.ResourceNotFoundException;
import com.rahul.blog.app.payload.CommentDTO;
import com.rahul.blog.app.repo.CommentsRepo;
import com.rahul.blog.app.repo.PostRepo;
import com.rahul.blog.app.repo.UserRepo;
import com.rahul.blog.app.service.CommentService;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentsRepo commentsRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	PostRepo postRepo;

	@Autowired
	ModelMapper mapper;

	private Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);

	
	
	  @Override public CommentDTO createComment(Integer userId, Integer postId,
	  CommentDTO commentDto) {
	  
	  log.info("[CommentServiceImpl] inside  createComment  "); User user =
	  userRepo.findById(userId).orElseThrow(() -> new
	  ResourceNotFoundException("User", "Id", userId));
	  log.info("[createComment] Get User  {}", user.toString()); Post post =
	  postRepo.findById(postId).orElseThrow(() -> new
	  ResourceNotFoundException("Post Id", "Id", postId));
	  
	  log.info("[createComment] Get Post  {}", post.toString()); Comment comment =
	  mapper.map(commentDto, Comment.class); comment.setPost(post);
	  comment.setUser(user); Comment commentNew = commentsRepo.save(comment);
	  log.info("[createComment] Get New Comment  {}", commentNew); return
	  mapper.map(commentNew, CommentDTO.class); }
	 
	
	/*
	 * @Override public CommentDTO createComment(Integer postId, CommentDTO
	 * commentDto) {
	 * 
	 * log.info("[CommentServiceImpl] inside  createComment  "); Post post =
	 * postRepo.findById(postId).orElseThrow(() -> new
	 * ResourceNotFoundException("Post Id", "Id", postId));
	 * log.info("[createComment] Get Post  {}", post.toString()); Comment comment =
	 * mapper.map(commentDto, Comment.class);
	 * 
	 * comment.setPost(post);
	 * 
	 * Comment savedComment = commentsRepo.save(comment);
	 * 
	 * log.info("[createComment] Get New Comment  {}", savedComment);
	 * 
	 * return mapper.map(savedComment, CommentDTO.class); }
	 */

	/*
	 * @Override public CommentDTO updateComment(Integer id, CommentDTO commentDto)
	 * { Comment comment = commentsRepo.findById(id) .orElseThrow(() -> new
	 * ResourceNotFoundException("Comment", "Id", id));
	 * comment.setComment(commentDto.getComment()); Comment updatedComment =
	 * commentsRepo.save(comment);
	 * 
	 * return mapper.map(updatedComment, CommentDTO.class); }
	 */
	/*
	 * @Override public CommentDTO getCommentById(Integer id) { Comment Comment =
	 * commentsRepo.findById(id) .orElseThrow(() -> new
	 * ResourceNotFoundException("Comment", "Id", id)); return mapper.map(Comment,
	 * CommentDTO.class); }
	 */

	/*
	 * @Override public List<CommentDTO> getAllComments() { List<Comment>
	 * listOfComments = commentsRepo.findAll(); List<CommentDTO> listOfDTO =
	 * listOfComments.stream().map(list -> mapper.map(list, CommentDTO.class))
	 * .collect(Collectors.toList()); return listOfDTO; }
	 */
	@Override
	public void deleteComment(Integer id) {
		Comment comment = commentsRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", id));
		commentsRepo.delete(comment);

	}

	/*
	 * @Override public List<CommentDTO> getCommentsByUser(Integer userId) { // TODO
	 * Auto-generated method stub User user =
	 * userRepo.findById(userId).orElseThrow(() -> new
	 * ResourceNotFoundException("User", "Id", userId));
	 * 
	 * List<Comment> listOfComments = commentsRepo.findByUser(user);
	 * List<CommentDTO> listOfDTO = listOfComments.stream().map(list ->
	 * mapper.map(list, CommentDTO.class)) .collect(Collectors.toList()); return
	 * listOfDTO; }
	 * 
	 * @Override public List<CommentDTO> getCommentsByPost(Integer postId) { Post
	 * post = postRepo.findById(postId).orElseThrow(() -> new
	 * ResourceNotFoundException("Post", "Id", postId));
	 * 
	 * List<Comment> listOfComments = commentsRepo.findByPost(post);
	 * List<CommentDTO> listOfDTO = listOfComments.stream().map(list ->
	 * mapper.map(list, CommentDTO.class)) .collect(Collectors.toList()); return
	 * listOfDTO; }
	 */

}
