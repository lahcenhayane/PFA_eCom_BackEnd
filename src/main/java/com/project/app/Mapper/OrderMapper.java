package com.project.app.Mapper;

import java.util.stream.Collectors;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.app.DTO.AddressDTO;
import com.project.app.DTO.OrderDTO;
import com.project.app.Entities.OrderEntity;


@Component
public class OrderMapper implements Mapper<OrderEntity, OrderDTO>{

	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	LineOrderMapper lineOrderMapper;
	
	
	/*************************************** TO_DTO **************************************/
	@Override
	public OrderDTO CONVERT_FROM_ENTITY_TO_DTO(OrderEntity e) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setId(e.getId());
		orderDTO.setTotal(e.getTotal());
		return orderDTO;
	}
	public OrderDTO CONVERT_FROM_ENTITY_TO_DTO_WITH_ADDRESS(OrderEntity e) {
		OrderDTO orderDTO = CONVERT_FROM_ENTITY_TO_DTO(e);
		orderDTO.setAddress(addressMapper.CONVERT_FROM_ENTITY_TO_DTO(e.getAddress()));
		return orderDTO;
	}
	
	public OrderDTO CONVERT_FROM_ENTITY_TO_DTO_WITH_ADDRESS_AND_USER(OrderEntity e) {
		OrderDTO orderDTO = CONVERT_FROM_ENTITY_TO_DTO_WITH_ADDRESS(e);
		orderDTO.setUser(userMapper.CONVERT_FROM_ENTITY_TO_DTO(e.getUser()));
		return orderDTO;
	}
	public OrderDTO CONVERT_FROM_ENTITY_TO_DTO_WITH_ADDRESS_AND_USER_AND_LINEORDER(OrderEntity e) {
		OrderDTO orderDTO = CONVERT_FROM_ENTITY_TO_DTO_WITH_ADDRESS_AND_USER(e);
		orderDTO.setLinesOrders(e.getLinesOrders().stream().map(lineOrderMapper::CONVERT_FROM_ENTITY_TO_DTO_WITH_PRODUCT).collect(Collectors.toList()));
		return orderDTO;
	}
	/*************************************** END DTO **************************************/
	
	
	/*************************************** TO_ENTITY **************************************/
	@Override
	public OrderEntity CONVERT_FROM_DTO_TO_ENTITY(OrderDTO d) {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setId(d.getId());
		orderEntity.setTotal(d.getTotal());
		return orderEntity;
	}
	/*************************************** END ENTITY **************************************/
}
