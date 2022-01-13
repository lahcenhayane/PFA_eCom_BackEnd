package com.project.app.Services;

import com.project.app.DTO.AddressDTO;

public interface IAddressService {

    AddressDTO createAddress(AddressDTO addressDTO);

    AddressDTO findById(long id);
}
