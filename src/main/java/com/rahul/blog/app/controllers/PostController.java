package com.rahul.blog.app.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
import com.rahul.blog.app.payload.PostDto;
import com.rahul.blog.app.payload.PostResponce;
import com.rahul.blog.app.service.FileService;
import com.rahul.blog.app.service.PostService;
import com.rahul.blog.app.util.AppConstants;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto dto, @PathVariable Integer userId,
			 @PathVariable Integer categoryId) {
		PostDto post = postService.createPost(dto, userId, categoryId);
		return new ResponseEntity<PostDto>(post, HttpStatus.CREATED);
	}

	
	//get by user
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByuser(@PathVariable Integer userId) {
		List<PostDto> post = postService.getAllPostByuser(userId);
		return new ResponseEntity<List<PostDto>>(post, HttpStatus.OK);
	}
	
	
	//get by category
	
	@GetMapping("/category/{categoryId}/posts")

	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
		List<PostDto> post = postService.getAllPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(post, HttpStatus.OK);
	}
	
	//getAll
	@GetMapping("/posts")
	public ResponseEntity<PostResponce> getAllPosts(
			@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir) {
		PostResponce post = postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponce>(post, HttpStatus.OK);
	}
	
	@GetMapping("/posts/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer id) {
		PostDto post = postService.getPostById(id);
		return new ResponseEntity<PostDto>(post, HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{id}")
	public ResponseEntity<ApiResponce> deletePostById(@PathVariable Integer id) {
		postService.deletePost(id);
		ApiResponce responce=new ApiResponce();
		responce.setMessage("Succesfully Deleted..........");
		responce.setSuccess(true);
		return new ResponseEntity<ApiResponce>(responce, HttpStatus.OK);
	}
	
	
	//Update
	
	@PutMapping("/posts/{id}")
	public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto dto, @PathVariable Integer id) {
		PostDto updatedPost=postService.updatePost(id, dto);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}
	
	
	@GetMapping("/posts/search/{kayword}")
	public ResponseEntity<List<PostDto>> searchPost(@PathVariable String kayword) {
		List<PostDto> post = postService.searchPost(kayword);
		return new ResponseEntity<List<PostDto>>(post, HttpStatus.OK);
	}
	
	
	//post image upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(
			@RequestParam(name = "image") MultipartFile file,
			@PathVariable Integer postId) throws IOException{
		PostDto dto=postService.getPostById(postId);
		String fileName=fileService.uploadImage(path, file);
		dto.setImageName(fileName);
		PostDto updatedPost=postService.updatePost(postId, dto);
		
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "/post/image/{imageName}",produces = {MediaType.IMAGE_JPEG_VALUE})
	public void getImage(
			@PathVariable String imageName,
			HttpServletResponse response) throws IOException{
		InputStream resource=fileService.getResource(path,imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream()); 
		
	}
	

}
