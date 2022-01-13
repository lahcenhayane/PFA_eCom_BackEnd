package com.project.app.DTO;

import java.util.List;

import javax.validation.constraints.PositiveOrZero;


public class OrderDTO {

	private long id;
	
	@PositiveOrZero
	private Double total = 0.0;
	
	private List<LineOrderDTO> linesOrders;
	private AddressDTO address;
	private UserDTO user;

	public OrderDTO() {
		super();
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public List<LineOrderDTO> getLinesOrders() {
		return linesOrders;
	}
	public void setLinesOrders(List<LineOrderDTO> linesOrders) {
		this.linesOrders = linesOrders;
	}
	public AddressDTO getAddress() {
		return address;
	}
	public void setAddress(AddressDTO address) {
		this.address = address;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}

}
