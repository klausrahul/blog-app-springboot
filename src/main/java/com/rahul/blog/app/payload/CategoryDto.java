package com.rahul.blog.app.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
	private Integer id;
	
	@NotBlank
	@Size(min = 4 , message = "Minimum size should be 4")
	private String categoryTitle;
	@NotBlank
	@Size(min = 10,message = "Minimum size should be  10")
	private String categoryDescription;
	@Override
	public String toString() {
		return "CategoryDto [id=" + id + ", categoryTitle=" + categoryTitle + ", categoryDescription="
				+ categoryDescription + "]";
	}
	
	
}
