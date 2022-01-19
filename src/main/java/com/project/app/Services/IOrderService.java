package com.project.app.Services;

import org.springframework.data.domain.Page;

import com.project.app.DTO.OrderDTO;
import com.project.app.Entities.OrderEntity;

public interface IOrderService {

	Page<OrderEntity> getOrders(int page, int size);

    OrderDTO createOrder(OrderDTO orderDTO);

	Long getCountOrders();

	Page<OrderDTO> getOrdersByUserID(Long id, int page, int size);
}
