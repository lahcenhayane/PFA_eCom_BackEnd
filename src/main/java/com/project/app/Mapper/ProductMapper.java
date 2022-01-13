package com.project.app.Mapper;

import org.springframework.stereotype.Component;

import com.project.app.DTO.CategoryDTO;
import com.project.app.DTO.ProductDTO;
import com.project.app.Entities.CategoryEntity;
import com.project.app.Entities.ProductEntity;

@Component
public class ProductMapper implements Mapper<ProductEntity, ProductDTO>{

//	@Autowired
//	private CategoryMapper categoryMapper;
//
//	public ProductMapper(CategoryMapper categoryMapper) {
//		this.categoryMapper = categoryMapper;
//	}

	@Override
	public ProductDTO CONVERT_FROM_ENTITY_TO_DTO(ProductEntity e) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(e.getId());
		productDTO.setTitle(e.getTitle());
		productDTO.setDescription(e.getDescription());
		productDTO.setPrice(e.getPrice());
		productDTO.setPhoto(e.getPhoto());
		productDTO.setQuantity(e.getQuantity());
		productDTO.setOutstock(e.getOutstock());
		productDTO.setProducturl(e.getProducturl());
		return productDTO;
	}
	public ProductDTO CONVERT_FROM_ENTITY_TO_DTO_WITH_CATEGORY(ProductEntity e) {
		ProductDTO product = this.CONVERT_FROM_ENTITY_TO_DTO(e);

		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(e.getCategory().getId());
		categoryDTO.setName(e.getCategory().getName());
		categoryDTO.setCategoryurl(e.getCategory().getCategoryurl());
		categoryDTO.setDescription(e.getCategory().getDescription());

		product.setCategory(categoryDTO);
		return product;
	}





	@Override
	public ProductEntity CONVERT_FROM_DTO_TO_ENTITY(ProductDTO d) {
		ProductEntity productEntity = new ProductEntity();
		productEntity.setTitle(d.getTitle());
		productEntity.setDescription(d.getDescription());
		productEntity.setPrice(d.getPrice());
		productEntity.setPhoto(d.getPhoto());
		productEntity.setQuantity(d.getQuantity());
		productEntity.setOutstock(d.getOutstock());
		productEntity.setProducturl(d.getProducturl());
		return productEntity;
	}
	public ProductEntity CONVERT_FROM_DTO_TO_ENTITY_WITH_CATEGORY(ProductDTO d) {
		ProductEntity productEntity = this.CONVERT_FROM_DTO_TO_ENTITY(d);

		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.setName(d.getCategory().getName());
		categoryEntity.setCategoryurl(d.getCategory().getCategoryurl());
		categoryEntity.setDescription(d.getCategory().getDescription());

		productEntity.setCategory(categoryEntity);
		return productEntity;
	}

}
