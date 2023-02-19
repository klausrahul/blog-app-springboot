package com.rahul.blog.app.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rahul.blog.app.entity.Category;
import com.rahul.blog.app.exception.ResourceNotFoundException;
import com.rahul.blog.app.payload.CategoryDto;
import com.rahul.blog.app.repo.CategoryRepo;
import com.rahul.blog.app.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo repo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CategoryDto createCategory(CategoryDto dto) {

		Category savedCategory = repo.save(dtoToCategory(dto));

		return categoryToDto(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(Integer id, CategoryDto categoryDto) {
		Category category = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());

		return categoryToDto(category);
	}

	@Override
	public CategoryDto getCategoryById(Integer id) {
		Category category = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category Not Found with Category id " + id));
		return categoryToDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		// TODO Auto-generated method stub

		List<Category> categoryList = repo.findAll();

		List<CategoryDto> categoryDto = categoryList.stream().map(cat -> this.categoryToDto(cat))
				.collect(Collectors.toList());

		return categoryDto;
	}

	@Override
	public void deleteCategory(Integer id) {

		Category category = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id));
		repo.delete(category);

		// repo.deleteById(id);
	}

	private Category dtoToCategory(CategoryDto dto) {
		Category category = mapper.map(dto, Category.class);
		return category;
	}

	private CategoryDto categoryToDto(Category category) {
		CategoryDto dto = mapper.map(category, CategoryDto.class);
		return dto;
	}
}
