package com.rahul.blog.app.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rahul.blog.app.entity.Category;
import com.rahul.blog.app.entity.Post;
import com.rahul.blog.app.entity.User;
import com.rahul.blog.app.exception.ResourceNotFoundException;
import com.rahul.blog.app.payload.PostDto;
import com.rahul.blog.app.payload.PostResponce;
import com.rahul.blog.app.repo.CategoryRepo;
import com.rahul.blog.app.repo.PostRepo;
import com.rahul.blog.app.repo.UserRepo;
import com.rahul.blog.app.service.PostService;

@Service
@Transactional
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	UserRepo userRepo;
	@Autowired
	CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));

		Post post = mapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setUser(user);

		Post newPost= postRepo.save(post);

		return mapper.map(newPost, PostDto.class );
	}

	@Override
	public PostDto updatePost(Integer id, PostDto postDto) {
		Post post=postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatedPost=postRepo.save(post);
		
		return mapper.map(updatedPost, PostDto.class);
	}

	@Override
	public PostDto getPostById(Integer id) {
			Post post=postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
		return mapper.map(post, PostDto.class);
	}

	@Override
	public PostResponce getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		
		Sort s=null;
		if(sortDir.equalsIgnoreCase("asc")) {
			 s =Sort.by(sortBy).ascending();
		}else {
			 s =Sort.by(sortBy).descending();
		}
		Pageable p=PageRequest.of(pageNumber, pageSize ,s);
		
		
		Page<Post> page=	postRepo.findAll(p);
		List<Post> 	listOfPost=page.getContent();
		List<PostDto> listOfPostDto=listOfPost.stream().map(post -> mapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponce responce=new PostResponce();
		responce.setContent(listOfPostDto);
		responce.setPageNumber(page.getNumber());
		responce.setPageSize(page.getSize());
		responce.setTotelElements(page.getTotalElements());
		responce.setTotalPages(page.getTotalPages());
		responce.setLastPage(page.isLast());
		
		
		return responce;
	}

	@Override
	
	public void deletePost(Integer id) {
		Post post=postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
		postRepo.delete(post);

	}

	@Override
	public List<PostDto> getAllPostByCategory(Integer CategoryId) {
		Category cat = categoryRepo.findById(CategoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", CategoryId));
		List<Post> posts=postRepo.findByCategory(cat);
		List<PostDto> postDto=posts.stream().map(post->mapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDto> getAllPostByuser(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		List<Post> posts=postRepo.findByUser(user);
		List<PostDto> postDto=posts.stream().map(post->mapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> posts=postRepo.findByTitleContaining(keyword);
		
		List<PostDto> listOfPosts	=posts.stream().map(post -> mapper.map(post, PostDto.class)).collect(Collectors.toList());
		return listOfPosts;
	}

}
