package com.project.app.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.app.DTO.AddressDTO;
import com.project.app.Services.IAddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private IAddressService addressService;

	@PostMapping
	public ResponseEntity<AddressDTO> create_address(@RequestBody AddressDTO addressDTO){
		return new ResponseEntity<>(addressService.createAddress(addressDTO), HttpStatus.CREATED);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<AddressDTO> findById(@PathVariable long id){
		return new ResponseEntity<>(addressService.findById(id), HttpStatus.OK);
	}
}
