package com.project.app.DTO;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CategoryDTO {

	private long id;
	private String categoryurl;
	@NotNull
	@Min(value = 2, message = "Le nom doit etre superieur à 2 caractere.")
	@Min(value = 49, message = "Le nom doit etre inferieur à 49 caractere.")
	private String name;

	@NotNull
	@Min(value = 2, message = "La description doit etre superieur à 2 caractere.")
	@Min(value = 199, message = "La description doit etre inferieur à 199 caractere.")
	private String description;

	private List<ProductDTO> products;

	public CategoryDTO() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getCategoryurl() {
		return categoryurl;
	}
	public void setCategoryurl(String categoryurl) {
		this.categoryurl = categoryurl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<ProductDTO> getProducts() {
		return products;
	}
	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}


}
