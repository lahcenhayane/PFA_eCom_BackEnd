package com.project.app.Services.Impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.project.app.DTO.CategoryDTO;
import com.project.app.Entities.CategoryEntity;
import com.project.app.Exceptions.ConflictException;
import com.project.app.Exceptions.NotFoundException;
import com.project.app.Exceptions.App.AppErrors;
import com.project.app.Mapper.CategoryMapper;
import com.project.app.Repositories.CategoryRepository;
import com.project.app.Services.ICategoryService;


@Service
public class CategoryService implements ICategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CategoryMapper categoryMapper;


	@Override
	public Page<CategoryDTO> getCategories(int page, int size) {
		Page<CategoryEntity> categories_entity = categoryRepository.findAll(PageRequest.of(page, size));
		return categories_entity.map(categoryMapper::CONVERT_FROM_ENTITY_TO_DTO);
	}

	@Override
	public CategoryDTO getCategoryByName(String category) {
		CategoryEntity categoryEntity = CHECK_CATEGORY_BY_CATEGORYURL(category.toLowerCase());
		return categoryMapper.CONVERT_FROM_ENTITY_TO_DTO(categoryEntity);
	}

	@Override
	public CategoryDTO getCategoryByID(Long id) {
		CategoryEntity categoryEntity = this.CHECK_CATEGORY_BY_ID(id);
		return categoryMapper.CONVERT_FROM_ENTITY_TO_DTO(categoryEntity);
	}

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		CategoryEntity categoryEntity = categoryRepository.findByName(categoryDTO.getName());
		if (categoryEntity != null) throw new NotFoundException(AppErrors.CATEGORY_ALREADY_ESIXT);

		String url = categoryDTO.getName().replace(" ", "-").toLowerCase();
		categoryDTO.setCategoryurl(url);

		CategoryEntity category = categoryRepository.save(categoryMapper.CONVERT_FROM_DTO_TO_ENTITY(categoryDTO));

		return categoryMapper.CONVERT_FROM_ENTITY_TO_DTO(category);
	}


	@Override
	public CategoryDTO editCategory(String category, CategoryDTO categoryDTO) {
		CategoryEntity categoryEntity = this.CHECK_CATEGORY_BY_CATEGORYURL(category.toLowerCase());

		CategoryEntity name = categoryRepository.findByName(categoryDTO.getName());
		if(name != null && name.getName() != categoryEntity.getName()) throw new ConflictException(AppErrors.CATEGORY_ALREADY_ESIXT);

		categoryEntity.setName(categoryDTO.getName());
		categoryEntity.setDescription(categoryDTO.getDescription());
		String url = categoryDTO.getName().replace(" ", "-").toLowerCase();
		categoryEntity.setCategoryurl(url);
		return categoryMapper.CONVERT_FROM_ENTITY_TO_DTO(categoryRepository.save(categoryEntity));
	}


	@Override
	public void deleteCategory(String category) {
		CategoryEntity categoryEntity = CHECK_CATEGORY_BY_CATEGORYURL(category.toLowerCase());
		categoryRepository.delete(categoryEntity);
	}




	/***************************** Private Methods **************************/
	private CategoryEntity CHECK_CATEGORY_BY_CATEGORYURL(String category) {
		CategoryEntity categoryEntity = categoryRepository.findByCategoryurl(category);
		if (categoryEntity == null) throw new NotFoundException(AppErrors.CATEGORY_NOT_FOUND);
		return categoryEntity;
	}

	private CategoryEntity CHECK_CATEGORY_BY_ID(Long id) {
		try {
			return categoryRepository.findById(id).get();
		}catch (NoSuchElementException e) {
			throw new NotFoundException(AppErrors.CATEGORY_NOT_FOUND);
		}
	}




}
