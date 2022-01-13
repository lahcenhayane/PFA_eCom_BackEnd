package com.project.app.Services.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.app.DTO.LineOrderDTO;
import com.project.app.DTO.OrderDTO;
import com.project.app.DTO.ProductDTO;
import com.project.app.Entities.LineOrderEntity;
import com.project.app.Entities.OrderEntity;
import com.project.app.Entities.ProductEntity;
import com.project.app.Exceptions.NotFoundException;
import com.project.app.Exceptions.App.AppErrors;
import com.project.app.Mapper.AddressMapper;
import com.project.app.Mapper.LineOrderMapper;
import com.project.app.Mapper.OrderMapper;
import com.project.app.Mapper.ProductMapper;
import com.project.app.Repositories.LineOrderRepository;
import com.project.app.Repositories.ProductRepository;
import com.project.app.Services.ILineOrderService;
import com.project.app.Services.IOrderService;
import com.project.app.Services.IProductService;


@Service
public class LineOrderService implements ILineOrderService{

	@Autowired
	private LineOrderRepository lineOrderRepository;
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private IProductService productService;

	@Autowired
	private LineOrderMapper lineOrderMapper;
	


	@Transactional
	@Override
	public LineOrderDTO createOrder(LineOrderDTO lineOrderDTO, OrderEntity orderEntity) {
		// TODO: Find Product.
		ProductEntity productEntity = productRepository.findByProducturl(lineOrderDTO.getProduct().getProducturl());
		if (lineOrderDTO.getQuantity() >=  productEntity.getQuantity()) throw new NotFoundException("Quantity est superieur.");
		
		// TODO: Save Line Order.
		LineOrderEntity lineOrderEntity = lineOrderMapper.CONVERT_FROM_DTO_TO_ENTITY(lineOrderDTO);
		Double total = lineOrderEntity.getQuantity() * productEntity.getPrice();
		lineOrderEntity.setSubtotal(total);
		lineOrderEntity = lineOrderRepository.save(lineOrderEntity);
		
		// TODO: Add Product to Line Order.
		lineOrderEntity.setProduct(productEntity);
		
		// TODO: Add Order to Line Order.
		lineOrderEntity.setOrder(orderEntity);

		// TODO: Save.
		LineOrderEntity order = lineOrderRepository.save(lineOrderEntity);
		
		// TODO: Edit Quantity Of Product.
		if (order != null) {
			productService.editQuantity(productEntity.getProducturl(), lineOrderEntity.getQuantity());
		}
		
		//TODO: Convert to DTO.
		return lineOrderMapper.CONVERT_FROM_ENTITY_TO_DTO_WITH_PRODUCT(order);
	}
}
