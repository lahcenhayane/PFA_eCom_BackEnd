package com.project.app.Services;

import org.springframework.data.domain.Page;

import com.project.app.DTO.ProductDTO;

public interface IProductService {

	Page<ProductDTO> getProducts(int page, int size);

	ProductDTO getProductByProducturl(String producturl);

	ProductDTO createProduct(ProductDTO productDTO);

	ProductDTO editProduct(String producturl, ProductDTO productDTO);

	void deleteProduct(String producturl);
	
	
	void editQuantity(String producturl, int qte);

}
