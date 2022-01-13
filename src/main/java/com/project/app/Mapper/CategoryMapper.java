package com.project.app.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.app.DTO.CategoryDTO;
import com.project.app.Entities.CategoryEntity;


@Component
public class CategoryMapper implements Mapper<CategoryEntity, CategoryDTO>{

	@Autowired
	private ProductMapper productMapper;

	@Override
	public CategoryDTO CONVERT_FROM_ENTITY_TO_DTO(CategoryEntity e) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(e.getId());
		categoryDTO.setName(e.getName());
		categoryDTO.setCategoryurl(e.getCategoryurl());
		categoryDTO.setDescription(e.getDescription());
		return categoryDTO;
	}

//	public List<CategoryDTO> CONVERT_FROM_ENTITY_TO_DTO_WITH_PRODUCT(CategoryEntity e) {
//		CategoryDTO categoryDTO = this.CONVERT_FROM_ENTITY_TO_DTO(e);
//		return categoryDTO.getProducts().stream().map(productMapper::CONVERT_FROM_ENTITY_TO_DTO).collect(Collectors.toList());
//	}

	@Override
	public CategoryEntity CONVERT_FROM_DTO_TO_ENTITY(CategoryDTO d) {
		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.setId(d.getId());
		categoryEntity.setName(d.getName());
		categoryEntity.setCategoryurl(d.getCategoryurl());
		categoryEntity.setDescription(d.getDescription());
		return categoryEntity;
	}

}
