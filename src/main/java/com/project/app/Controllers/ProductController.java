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

import com.project.app.DTO.ProductDTO;
import com.project.app.Services.IProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private IProductService productService;

	@GetMapping
	public Page<ProductDTO> getProducts(@PathParam(value = "page") int page, @PathParam(value = "size") int size) {
		return productService.getProducts(page, size);
	}

	@GetMapping(path = "/{producturl}")
	public ResponseEntity<ProductDTO> getProductByProducturl(@PathVariable String producturl){
		return new ResponseEntity<>(productService.getProductByProducturl(producturl), HttpStatus.ACCEPTED);
	}

	@PostMapping
	public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
		return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
	}

	@PutMapping(path = "/{producturl}")
	public ResponseEntity<ProductDTO> editProduct(@PathVariable String producturl, @RequestBody ProductDTO productDTO){
		return new ResponseEntity<>(productService.editProduct(producturl, productDTO), HttpStatus.ACCEPTED);
	}

	@DeleteMapping(path = "/{producturl}")
	public ResponseEntity<?> deleteProduct(@PathVariable String producturl){
		productService.deleteProduct(producturl);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

//	@PostMapping(path = "/upload/{producturl}")
//	public ResponseEntity<?> uploadProduct(String producturl, @RequestParam("file") MultipartFile file){
//		return new ResponseEntity<>(HttpStatus.CREATED);
//	}
}
