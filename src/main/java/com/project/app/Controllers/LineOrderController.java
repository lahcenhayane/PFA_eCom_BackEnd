package com.project.app.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.app.DTO.LineOrderDTO;
import com.project.app.Mapper.LineOrderMapper;
import com.project.app.Services.ILineOrderService;

@RestController
@RequestMapping(name = "/lineorders")
public class LineOrderController {

//	@Autowired
//	private ILineOrderService lineOrderService;
//	@Autowired
//	private LineOrderMapper lineOrderMapper;
//
//
//	@PostMapping
//	public ResponseEntity<LineOrderDTO> create_order(@RequestBody LineOrderDTO lineOrderDTO){
//		return new ResponseEntity<>(lineOrderService.createOrder(lineOrderDTO), HttpStatus.CREATED);
//	}

}
