package com.project.app.Services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import com.project.app.DTO.AddressDTO;
import com.project.app.DTO.LineOrderDTO;
import com.project.app.DTO.OrderDTO;
import com.project.app.Entities.AddressEntity;
import com.project.app.Entities.LineOrderEntity;
import com.project.app.Entities.OrderEntity;
import com.project.app.Entities.UserEntity;
import com.project.app.Exceptions.NotFoundException;
import com.project.app.Exceptions.App.AppErrors;
import com.project.app.Mapper.AddressMapper;
import com.project.app.Mapper.LineOrderMapper;
import com.project.app.Mapper.OrderMapper;
import com.project.app.Mapper.ProductMapper;
import com.project.app.Repositories.OrderRepository;
import com.project.app.Repositories.UserRepository;
import com.project.app.Services.IAddressService;
import com.project.app.Services.ILineOrderService;
import com.project.app.Services.IOrderService;


@Service
public class OrderService implements IOrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private IAddressService addressService;
	@Autowired
	private ILineOrderService lineOrderService;
	
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private LineOrderMapper lineOrderMapper;


	
	@Override
	public Page<OrderEntity> getOrders(int page, int size) {
		return orderRepository.findAll(PageRequest.of(page, size));
	}

	public OrderEntity orderEntity = new OrderEntity();
	public Double total  = 0.0;
	@Transactional
	@Override
	public OrderDTO createOrder(OrderDTO orderDTO) {
//		orderDTO.setTotal(0.0);
		// TODO: Order.
		orderEntity = orderMapper.CONVERT_FROM_DTO_TO_ENTITY(orderDTO);
		orderEntity = orderRepository.save(orderEntity);

		//TODO: Get Current User.
		UserEntity user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		orderEntity.setUser(user);

		// TODO: Find Address.
		AddressDTO addressDTO = addressService.findById(orderDTO.getAddress().getId());
		AddressEntity addressEntity = addressMapper.CONVERT_FROM_DTO_TO_ENTITY(addressDTO);
		orderEntity.setAddress(addressEntity);		

		
		// TODO:Save Line Order with Product.
		List<LineOrderEntity> lineOrderEntity = new ArrayList<>();
		orderDTO.getLinesOrders().stream().forEach(row->{
			LineOrderEntity lineOrder = lineOrderMapper.CONVERT_FROM_DTO_TO_ENTITY_WITH_PRODUCT(lineOrderService.createOrder(row, orderEntity));
			
			lineOrderEntity.add(lineOrder);
			total += lineOrder.getSubtotal();
		});
		orderEntity.setLinesOrders(lineOrderEntity);
		
		// TODO: Sum Price of all Product.
		orderEntity.setTotal(total);

		// TODO: Save Order.
		OrderEntity order = orderRepository.save(orderEntity);
		
		// TODO: Return OrderDTO.
		return  orderMapper.CONVERT_FROM_ENTITY_TO_DTO_WITH_ADDRESS_AND_USER_AND_LINEORDER(order);
	}
	@Override
	public Long getCountOrders() {
		// TODO Auto-generated method stub
		return orderRepository.count();
	}
	@Override
	public Page<OrderDTO> getOrdersByUserID(Long id, int page, int size) {
		try {
			userRepository.findById(id).get();			
		}catch(NoSuchElementException e) {
			throw new NotFoundException(AppErrors.USERNAME_NOT_FOUND);
		}
		Page<OrderEntity> orders = orderRepository.getOrderByUserID(id, PageRequest.of(page, size));
		
		return orders.map(orderMapper::CONVERT_FROM_ENTITY_TO_DTO_WITH_ADDRESS);
	}

}
