package com.project.app.Mapper;

import org.springframework.stereotype.Component;

import com.project.app.DTO.AddressDTO;
import com.project.app.Entities.AddressEntity;


@Component
public class AddressMapper implements Mapper<AddressEntity, AddressDTO>{

	@Override
	public AddressDTO CONVERT_FROM_ENTITY_TO_DTO(AddressEntity e) {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setId(e.getId());
		addressDTO.setAddress(e.getAddress());
		addressDTO.setPays(e.getPays());
		addressDTO.setVille(e.getVille());
		addressDTO.setZipcode(e.getZipcode());
		return addressDTO;
	}

	@Override
	public AddressEntity CONVERT_FROM_DTO_TO_ENTITY(AddressDTO d) {
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setId(d.getId());
		addressEntity.setAddress(d.getAddress());
		addressEntity.setPays(d.getPays());
		addressEntity.setVille(d.getVille());
		addressEntity.setZipcode(d.getZipcode());
		return addressEntity;
	}

}
