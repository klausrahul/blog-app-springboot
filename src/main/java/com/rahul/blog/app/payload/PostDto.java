package com.rahul.blog.app.payload;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rahul.blog.app.entity.Comment;
import com.rahul.blog.app.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

	private String title;
	private String content;

	private String imageName;
	private Date addedDate;

	private CategoryDto category;

	private UserDto user;

	private Set<CommentDTO> comments = new HashSet<>();
	
	  
	  
	  @Override public String toString() { return "PostDto [title=" + title +
	  ", content=" + content + ", imageName=" + imageName + ", addedDate=" +
	  addedDate + ", category=" + category + ", user=" + user + ", comments=" +
	  comments + "]"; }
	 

}
