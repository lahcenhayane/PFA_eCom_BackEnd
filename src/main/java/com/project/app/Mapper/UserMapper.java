package com.project.app.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.project.app.DTO.OrderDTO;
import com.project.app.DTO.RoleDTO;
import com.project.app.DTO.UserDTO;
import com.project.app.Entities.OrderEntity;
import com.project.app.Entities.RoleEntity;
import com.project.app.Entities.UserEntity;

@Component
public class UserMapper implements Mapper<UserEntity, UserDTO>{

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private LineOrderMapper lineOrderMapper;
	
	private List<UserDTO> list_users_dto = new ArrayList<>();


	//---------------FROM ENTITY TO DTO
	public List<UserDTO> CONVERT_FROM_ENTITY_TO_DTO(List<UserEntity> list_users) {
		if (list_users.size() == 0) {
			return null;
		}
		return list_users.stream().map(this::CONVERT_FROM_ENTITY_TO_DTO_WITH_ROLES).collect(Collectors.toList());
	}

	@Override
	public UserDTO CONVERT_FROM_ENTITY_TO_DTO(UserEntity user) {
		if (user == null) {
			return null;
		}
		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		dto.setFirstname(user.getFirstname());
		dto.setLastname(user.getLastname());
		dto.setUsername(user.getUsername());
		dto.setEmail(user.getEmail());
		dto.setPassword("");
		dto.setEnabled(user.getEnabled());
		dto.setPhone(user.getPhone());
		dto.setCity(user.getCity());
		return dto;
	}

	public UserDTO CONVERT_FROM_ENTITY_TO_DTO_WITH_ROLES(UserEntity user){
		UserDTO userDto = this.CONVERT_FROM_ENTITY_TO_DTO(user);
		Set<RoleDTO> roleDto = user.getRoles()
									.stream()
									.map(row -> this.roleMapper.CONVERT_FROM_ENTITY_TO_DTO(row))
									.collect(Collectors.toSet());
		userDto.setRoles(roleDto);
		return userDto;
	}
	public UserDTO CONVERT_FROM_ENTITY_TO_DTO_WITH_ROLES_AND_ORDERS(UserEntity user){
		UserDTO users =  CONVERT_FROM_ENTITY_TO_DTO_WITH_ROLES(user);
		
//		List<OrderDTO> orders = new ArrayList<>();
//		users.getOrders().stream().forEach(row->{
//			OrderDTO orderDTO = new OrderDTO();
//			orderDTO.setId(row.getId());
//			orderDTO.setTotal(row.getTotal());
//			orderDTO.setLinesOrders(row.getLinesOrders().stream().map(lineOrderMapper::CONVERT_FROM_ENTITY_TO_DTO_WITH_PRODUCT).collect(Collectors.toList()));
//			orders.add(orderDTO);
//		});
		
		return users;
	}


	//---------------FROM DTO TO ENTITY
	public List<UserEntity> CONVERT_FROM_DTO_TO_ENTITY(List<UserDTO> list_users) {
		if (list_users.size() == 0) {
			return null;
		}
		return list_users.stream().map(this::CONVERT_FROM_DTO_TO_ENTITY_WITH_ROLES).collect(Collectors.toList());
	}

    @Override
	public UserEntity CONVERT_FROM_DTO_TO_ENTITY(UserDTO dto) {
		if (dto == null) {
			return null;
		}
		UserEntity userEntity = new UserEntity();
		userEntity.setFirstname(dto.getFirstname());
		userEntity.setLastname(dto.getLastname());
		userEntity.setUsername(dto.getUsername());
		userEntity.setEmail(dto.getEmail());
		userEntity.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
		userEntity.setEnabled(dto.getEnabled());
		userEntity.setPhone(dto.getPhone());
		userEntity.setCity(dto.getCity());
		return userEntity;
	}

	public UserEntity CONVERT_FROM_DTO_TO_ENTITY_WITH_ROLES(UserDTO userDTO) {
		UserEntity userEntity = CONVERT_FROM_DTO_TO_ENTITY(userDTO);
		Set<RoleEntity> roleEntities = userDTO.getRoles().stream().map(row->this.roleMapper.CONVERT_FROM_DTO_TO_ENTITY(row)).collect(Collectors.toSet());
		userEntity.setRoles(roleEntities);
		return userEntity;
	}


}
