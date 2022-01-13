package com.project.app.Controllers;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.app.DTO.OrderDTO;
import com.project.app.Entities.OrderEntity;
import com.project.app.Services.IOrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private IOrderService orderService;

	@GetMapping
	public Page<OrderEntity> getOrders(@PathParam(value = "page") int page, @PathParam(value = "size") int size){
		return orderService.getOrders(page, size);
	}

	
	@PostMapping
	public ResponseEntity<OrderDTO> create_order(@RequestBody OrderDTO orderDTO){
		return new ResponseEntity<>(orderService.createOrder(orderDTO), HttpStatus.CREATED);
	}
}
