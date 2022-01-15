package com.project.app.Controllers;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.app.DTO.UserDTO;
import com.project.app.Services.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping("/count")
	public ResponseEntity<Long> getCountUser(){
		return new ResponseEntity<>(userService.getCountUser(),HttpStatus.OK);
	}

	@GetMapping
	public Page<UserDTO> getUsers(@PathParam(value = "page") int page, @PathParam(value = "size") int size){
		return userService.getUsers(page, size);
	}

	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO dto){
		return new ResponseEntity<>(userService.createUser(dto), HttpStatus.CREATED);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
		return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<UserDTO> editUser(@PathVariable Long id, @RequestBody UserDTO userDTO){
		return new ResponseEntity<>(userService.editUser(id, userDTO), HttpStatus.ACCEPTED);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id){
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
