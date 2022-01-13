package com.project.app.Services;

import java.util.List;

import com.project.app.DTO.LineOrderDTO;
import com.project.app.DTO.OrderDTO;
import com.project.app.Entities.OrderEntity;

public interface ILineOrderService {

	LineOrderDTO createOrder(LineOrderDTO lineOrderDTO, OrderEntity orderEntity);
	
}
