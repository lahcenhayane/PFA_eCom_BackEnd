package com.project.app.Services;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.project.app.DTO.UserDTO;

public interface IUserService extends UserDetailsService {


	public Page<UserDTO> getUsers(int page, int size);
	public UserDTO createUser(UserDTO dto);
	public UserDTO getUser(Long id);
	public UserDTO editUser(Long id, UserDTO userDTO);
	public void deleteUser(Long id);
	public Long getCountUser();
}
