package com.project.app.Services.Impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.app.DTO.UserDTO;
import com.project.app.Entities.RoleEntity;
import com.project.app.Entities.UserEntity;
import com.project.app.Entities.Enums.RoleEnum;
import com.project.app.Exceptions.AuthException;
import com.project.app.Exceptions.ConflictException;
import com.project.app.Exceptions.NotFoundException;
import com.project.app.Exceptions.App.AppErrors;
import com.project.app.Mapper.UserMapper;
import com.project.app.Repositories.RoleRepository;
import com.project.app.Repositories.UserRepository;
import com.project.app.Services.IUserService;


@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserMapper userMapper;


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByEmail(email);
		if (user == null) throw new AuthException(AppErrors.USERNAME_NOT_FOUND);
		if (user.getEnabled()) throw new AuthException(AppErrors.USER_ENABLE);
		return new User(user.getEmail(), user.getPassword(), this.getRoles(user));
	}

	private Collection<? extends GrantedAuthority> getRoles(UserEntity user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().stream().forEach(row->{
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+row.getName().toUpperCase());
			authorities.add(grantedAuthority);
		});
		return authorities;
	}

	@Override
	public Page<UserDTO> getUsers(int page, int size) {
		Page<UserEntity> list_users = userRepository.findAll(PageRequest.of(page, size));
		return list_users.map(userMapper::CONVERT_FROM_ENTITY_TO_DTO);
	}

	@Override
	public UserDTO createUser(UserDTO dto) {
		UserEntity user = userRepository.findByUsername(dto.getUsername());
		if (user != null) throw new ConflictException(AppErrors.USERNAME_ALREADY_ESIXT);
		UserEntity email = userRepository.findByEmail(dto.getEmail());
		if (email != null) throw new ConflictException(AppErrors.EMAIL_ALREADY_ESIXT);

		UserEntity userEntity = userMapper.CONVERT_FROM_DTO_TO_ENTITY(dto);
		userEntity.setEnabled(false);
		UserEntity userEntity2 = userRepository.save(userEntity);
		RoleEntity roleEntity = roleRepository.findByName(RoleEnum.CLIENT.toString());
		userEntity2.getRoles().add(roleEntity);
		roleEntity.getUsers().add(userEntity2);
		return userMapper.CONVERT_FROM_ENTITY_TO_DTO_WITH_ROLES(userRepository.save(userEntity2));
	}

	@Override
	public UserDTO getUser(Long id) {
		UserEntity user = this.CHECK_USER_IF_EXIST(id);

		if (user == null) throw new NotFoundException(AppErrors.USERNAME_NOT_FOUND);
		UserDTO userDTO = userMapper.CONVERT_FROM_ENTITY_TO_DTO_WITH_ROLES(user);
		return userDTO;
	}

	@Override
	public UserDTO editUser(Long id, UserDTO userDTO) {
		UserEntity user = this.CHECK_USER_IF_EXIST(id);

		UserEntity emailEntity = userRepository.findByEmail(userDTO.getEmail());
		if (emailEntity != null && emailEntity.getId() != user.getId()) throw new ConflictException(AppErrors.EMAIL_ALREADY_ESIXT);

		UserEntity usernameEntity = userRepository.findByUsername(userDTO.getUsername());
		if (usernameEntity != null && usernameEntity.getId() != user.getId()) throw new ConflictException(AppErrors.USERNAME_ALREADY_ESIXT);

//		user = userMapper.CONVERT_FROM_DTO_TO_ENTITY(userDTO);

		user.setFirstname(userDTO.getFirstname());
		user.setLastname(userDTO.getLastname());
		user.setPhone(userDTO.getPhone());
		user.setCity(userDTO.getCity());
		user.setUsername(userDTO.getUsername());
		user.setEmail(userDTO.getEmail());

		return userMapper.CONVERT_FROM_ENTITY_TO_DTO_WITH_ROLES(userRepository.save(user));
	}

	@Override
	public void deleteUser(Long id) {
		UserEntity user = userRepository.findById(id).get();
		if (user == null) throw new NotFoundException(AppErrors.USERNAME_NOT_FOUND);
		userRepository.delete(user);
	}



	/***************** Private Methods ******************/
	private UserEntity CHECK_USER_IF_EXIST(Long id){
		try {
			return userRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new NotFoundException(AppErrors.USERNAME_NOT_FOUND +": "+e.getMessage());
		}
	}

}
