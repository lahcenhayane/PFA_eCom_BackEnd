package com.project.app.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "orders")
public class OrderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private Double total;

	@OneToMany(mappedBy = "order")
	private List<LineOrderEntity> linesOrders;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "address_id")
	private AddressEntity address;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	public OrderEntity() {
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

	public List<LineOrderEntity> getLinesOrders() {
		return linesOrders;
	}

	public void setLinesOrders(List<LineOrderEntity> linesOrders) {
		this.linesOrders = linesOrders;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}
