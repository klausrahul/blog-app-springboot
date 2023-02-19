package com.rahul.blog.app.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponce {
	
	private List<PostDto> content;
	private int pageNumber;
	private int pageSize;
	private long totelElements;
	private int totalPages;
	private boolean lastPage;
	@Override
	public String toString() {
		return "PostResponce [content=" + content + ", pageNumber=" + pageNumber + ", pageSize=" + pageSize
				+ ", totelElements=" + totelElements + ", totalPages=" + totalPages + ", lastPage=" + lastPage + "]";
	}
	

}
