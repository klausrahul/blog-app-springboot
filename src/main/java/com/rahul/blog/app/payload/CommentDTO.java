package com.rahul.blog.app.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CommentDTO {

	private int id;
	private String comment;
	/*
	 * private PostDto post;
	 * 
	 * private UserDto user;
	 */
	@Override
	public String toString() {
		return "CommentDTO [id=" + id + ", comment=" + comment + "]";
	}

	
	
}
