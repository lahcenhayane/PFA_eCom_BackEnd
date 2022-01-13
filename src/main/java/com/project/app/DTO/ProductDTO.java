package com.project.app.DTO;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.format.annotation.DateTimeFormat;

public class ProductDTO {

	private long id;
	private String producturl;
	@NotNull
	private String title;
	@NotNull
	private String description;

	private String photo;
	@PositiveOrZero
	@NotNull
	private Double price;
	@PositiveOrZero
	@NotNull
	private int quantity;
	private Boolean outstock;
	@DateTimeFormat(pattern = "dd/mm/yyyy hh:mm:ss")
	private Date created_at = new Date();

	private CategoryDTO category;

	public ProductDTO() {
		super();
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProducturl() {
		return producturl;
	}
	public void setProducturl(String producturl) {
		this.producturl = producturl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Boolean getOutstock() {
		return outstock;
	}
	public void setOutstock(Boolean outstock) {
		this.outstock = outstock;
	}
	public Date getCreated_at() {
		return created_at;
	}

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
	}


}
