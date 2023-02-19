package com.rahul.blog.app.service;

import java.util.List;

import com.rahul.blog.app.payload.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto user);

	CategoryDto updateCategory(Integer id, CategoryDto user);

	CategoryDto getCategoryById(Integer id);

	List<CategoryDto> getAllCategory();

	void deleteCategory (Integer id);
}
