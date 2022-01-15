package com.project.app.Services;

import org.springframework.data.domain.Page;

import com.project.app.DTO.CategoryDTO;

public interface ICategoryService {

	Page<CategoryDTO> getCategories(int page, int size);

	CategoryDTO getCategoryByName(String category);

	CategoryDTO getCategoryByID(Long id);

	CategoryDTO createCategory(CategoryDTO categoryDTO);

	void deleteCategory(String category);

	CategoryDTO editCategory(String category, CategoryDTO categoryDTO);

	Long getCountCaterogies();

}
