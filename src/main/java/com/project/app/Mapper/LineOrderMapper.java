package com.project.app.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.app.DTO.LineOrderDTO;
import com.project.app.Entities.LineOrderEntity;


@Component
public class LineOrderMapper implements Mapper<LineOrderEntity, LineOrderDTO>{

	@Autowired
	ProductMapper productMapper;
	
	
	/********************************* TO DTO **************************************/
	@Override
	public LineOrderDTO CONVERT_FROM_ENTITY_TO_DTO(LineOrderEntity e) {
		LineOrderDTO lineOrderDTO = new LineOrderDTO();
		lineOrderDTO.setId(e.getId());
		lineOrderDTO.setQuantity(e.getQuantity());
		lineOrderDTO.setSubtotal(e.getSubtotal());
		return lineOrderDTO;
	}
	public LineOrderDTO CONVERT_FROM_ENTITY_TO_DTO_WITH_PRODUCT(LineOrderEntity e) {
		LineOrderDTO lineOrderDTO = CONVERT_FROM_ENTITY_TO_DTO(e);
		lineOrderDTO.setProduct(productMapper.CONVERT_FROM_ENTITY_TO_DTO_WITH_CATEGORY(e.getProduct()));
		return lineOrderDTO;
	}
	/********************************* END DTO **************************************/
	
	
	/********************************* TO ENTITY **************************************/
	@Override
	public LineOrderEntity CONVERT_FROM_DTO_TO_ENTITY(LineOrderDTO d) {
		LineOrderEntity lineOrderEntity = new LineOrderEntity();
		lineOrderEntity.setId(d.getId());
		lineOrderEntity.setQuantity(d.getQuantity());
		lineOrderEntity.setSubtotal(d.getSubtotal());
		return lineOrderEntity;
	}
	public LineOrderEntity CONVERT_FROM_DTO_TO_ENTITY_WITH_PRODUCT(LineOrderDTO d) {
		LineOrderEntity lineOrderEntity = CONVERT_FROM_DTO_TO_ENTITY(d);
		lineOrderEntity.setProduct(productMapper.CONVERT_FROM_DTO_TO_ENTITY_WITH_CATEGORY(d.getProduct()));
		return lineOrderEntity;
	}
	/********************************* END ENTITY **************************************/
}
