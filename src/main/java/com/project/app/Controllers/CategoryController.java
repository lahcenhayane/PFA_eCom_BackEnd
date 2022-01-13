package com.project.app.Controllers;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.project.app.DTO.CategoryDTO;
import com.project.app.Services.ICategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;

	@GetMapping
	public Page<CategoryDTO> getCategories(@PathParam(value = "page") int page, @PathParam(value = "size") int size){
		return categoryService.getCategories(page, size);
	}

	@GetMapping("/{category}")
	public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String category) {
		return new ResponseEntity<>(categoryService.getCategoryByName(category), HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
		return new ResponseEntity<>(categoryService.getCategoryByID(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
		return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
	}

	@PutMapping(path = "/{category}")
	public ResponseEntity<CategoryDTO> editCategory(@PathVariable String category, @RequestBody CategoryDTO categoryDTO){
		return new ResponseEntity<>(categoryService.editCategory(category, categoryDTO), HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/{category}")
	public ResponseEntity<?> delete(@PathVariable String category) {
		categoryService.deleteCategory(category);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
