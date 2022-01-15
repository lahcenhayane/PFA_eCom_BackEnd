package com.project.app.Services.Impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.project.app.DTO.ProductDTO;
import com.project.app.Entities.CategoryEntity;
import com.project.app.Entities.ProductEntity;
import com.project.app.Exceptions.NotFoundException;
import com.project.app.Exceptions.App.AppErrors;
import com.project.app.Mapper.ProductMapper;
import com.project.app.Repositories.CategoryRepository;
import com.project.app.Repositories.ProductRepository;
import com.project.app.Services.IProductService;

@Service
public class ProductService implements IProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductMapper productMapper;

	@Override
	public Page<ProductDTO> getProducts(int page, int size) {
		Page<ProductEntity> products = productRepository.findAll(PageRequest.of(page, size));
		return products.map(productMapper::CONVERT_FROM_ENTITY_TO_DTO_WITH_CATEGORY);
	}

	@Override
	public ProductDTO getProductByProducturl(String producturl) {
		ProductEntity product = productRepository.findByProducturl(producturl);
		if (product == null) throw new NotFoundException(AppErrors.PRODUCT_NOT_FOUND + producturl);
		return productMapper.CONVERT_FROM_ENTITY_TO_DTO_WITH_CATEGORY(product);
	}

	@Transactional
	@Override
	public ProductDTO createProduct(ProductDTO productDTO) {
		ProductEntity product = productRepository.findByProducturl(productDTO.getTitle());
		if( product != null ) throw new NotFoundException(AppErrors.TITLE_PRODUCT_ALREADY_ESIXT);

		String url = productDTO.getTitle().replace(" ", "-");
		String date = new Date().getDay()+"-"+ new Date().getMonth() +"-"+ new Date().getYear()+"-"+new Date().getHours()+"-"+new Date().getMinutes()+"-"+new Date().getSeconds();
		productDTO.setProducturl(url+"-"+ date);
		if (productDTO.getQuantity() > 0) {
			productDTO.setOutstock(false);
		}else {
			productDTO.setOutstock(true);
		}

		/******************************** Start File **********************************/
//		if (file.getSize() > 2000) throw new ImageException(AppErrors.IMAGE_SIZE);
//		if (file.get)
//		String destinationfile = PropertiesGlobal.DIR + file.getOriginalFilename() + date;
//		try {
//			Files.copy(file.getInputStream(), Paths.get(destinationfile), StandardCopyOption.REPLACE_EXISTING);
//		}catch (IOException e) {
//			throw new RuntimeException(e.getMessage());
//		}
//		productDTO.setPhoto(destinationfile);
		/******************************** End File ************************************/

		ProductEntity productEntity = productMapper.CONVERT_FROM_DTO_TO_ENTITY(productDTO);
		productEntity = productRepository.save(productEntity);

		CategoryEntity categoryEntity = categoryRepository.findByCategoryurl(productDTO.getCategory().getCategoryurl());
		if (categoryEntity == null) throw new NotFoundException(AppErrors.CATEGORY_NOT_FOUND);

		productEntity.setCategory(categoryEntity);

		return productMapper.CONVERT_FROM_ENTITY_TO_DTO_WITH_CATEGORY(productRepository.save(productEntity));
	}

	@Override
	public ProductDTO editProduct(String producturl, ProductDTO productDTO) {
		ProductEntity product = productRepository.findByProducturl(producturl);
		if(product == null) throw new NotFoundException(AppErrors.PRODUCT_NOT_FOUND);

		String url = productDTO.getTitle().replace(" ", "-");
		String date = new Date().getDay()+"-"+ new Date().getMonth() +"-"+ new Date().getYear()+"-"+new Date().getHours()+"-"+new Date().getMinutes()+"-"+new Date().getSeconds();
		product.setProducturl(url+"-"+ date);
		if (productDTO.getQuantity() > 0) {
			product.setOutstock(false);
		}else {
			product.setOutstock(true);
		}
		product.setTitle(productDTO.getTitle());
		product.setDescription(productDTO.getDescription());
		product.setPrice(productDTO.getPrice());
		product.setQuantity(productDTO.getQuantity());

		CategoryEntity categoryEntity = categoryRepository.findByCategoryurl(productDTO.getCategory().getCategoryurl());
		if (categoryEntity == null) throw new NotFoundException(AppErrors.CATEGORY_NOT_FOUND);

		product.setCategory(categoryEntity);

		return productMapper.CONVERT_FROM_ENTITY_TO_DTO_WITH_CATEGORY(productRepository.save(product));
	}

	@Override
	public void deleteProduct(String producturl) {
		ProductEntity product = productRepository.findByProducturl(producturl);
		if(product == null) throw new NotFoundException(AppErrors.PRODUCT_NOT_FOUND);
		productRepository.delete(product);
	}

	@Override
	public void editQuantity(String producturl, int qte) {
		ProductEntity productEntity = productRepository.findByProducturl(producturl);
		if (productEntity == null) throw new NotFoundException(AppErrors.PRODUCT_NOT_FOUND);
		productEntity.setQuantity(productEntity.getQuantity() - qte);
		productRepository.save(productEntity);
		
	}

	@Override
	public Long getCountProducts() {
		// TODO Auto-generated method stub
		return productRepository.count();
	}



}
