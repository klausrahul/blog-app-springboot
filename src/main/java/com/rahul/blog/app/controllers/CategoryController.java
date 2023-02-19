package com.rahul.blog.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahul.blog.app.payload.ApiResponce;
import com.rahul.blog.app.payload.CategoryDto;
import com.rahul.blog.app.payload.UserDto;
import com.rahul.blog.app.service.CategoryService;
import com.rahul.blog.app.service.UserService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService service;
	
	
	//post
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto dto){
		CategoryDto cat=service.createCategory(dto);
		return new ResponseEntity<CategoryDto>(cat, HttpStatus.CREATED);
	}
	//put
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@PathVariable(name ="id" ) Integer id,@Valid @RequestBody CategoryDto dto){
		CategoryDto cat=service.updateCategory(id, dto);
		return new ResponseEntity<CategoryDto>(cat, HttpStatus.OK);
	}
	
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> findAll(){
		List<CategoryDto> cat=service.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(cat, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> findById(@PathVariable(name ="id" ) Integer id){
		CategoryDto cat=service.getCategoryById(id);
		return new ResponseEntity<CategoryDto>(cat, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponce> deleteUser(@PathVariable(name ="id" ) Integer id){
			this.service.deleteCategory(id);
		//return ResponseEntity.ok(Map.of("Message","user Deleted Successfully"));
			
			return new ResponseEntity<ApiResponce>(new ApiResponce("Category deleted Successfully", true), HttpStatus.OK);
	}
	

}
