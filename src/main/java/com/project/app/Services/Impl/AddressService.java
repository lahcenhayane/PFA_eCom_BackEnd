package com.project.app.Services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.app.DTO.AddressDTO;
import com.project.app.Entities.AddressEntity;
import com.project.app.Exceptions.NotFoundException;
import com.project.app.Exceptions.App.AppErrors;
import com.project.app.Mapper.AddressMapper;
import com.project.app.Repositories.AddressRepository;
import com.project.app.Services.IAddressService;


@Service
public class AddressService implements IAddressService {

	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private AddressMapper addressMapper;


	@Override
	public AddressDTO createAddress(AddressDTO addressDTO) {
		AddressEntity addressEntity = addressMapper.CONVERT_FROM_DTO_TO_ENTITY(addressDTO);
		return addressMapper.CONVERT_FROM_ENTITY_TO_DTO(addressRepository.save(addressEntity));
	}

	@Override
	public AddressDTO findById(long id) {
		AddressEntity addressEntity = addressRepository.findById(id).orElseGet(() -> {throw new NotFoundException(AppErrors.ADDRESS_NOT_FOUND);});
		return addressMapper.CONVERT_FROM_ENTITY_TO_DTO(addressEntity);
	}


}
