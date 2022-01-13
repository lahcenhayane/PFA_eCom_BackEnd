package com.project.app.Mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.project.app.DTO.RoleDTO;
import com.project.app.Entities.RoleEntity;


@Component
public class RoleMapper implements Mapper<RoleEntity, RoleDTO> {


	public List<RoleDTO> CONVERT_FROM_ENTITY_TO_DTO(List<RoleEntity> list) {
		return list.stream().map(this::CONVERT_FROM_ENTITY_TO_DTO).collect(Collectors.toList());
	}


	public List<RoleEntity> CONVERT_FROM_DTO_TO_ENTITY(List<RoleDTO> list) {
		return list.stream().map(this::CONVERT_FROM_DTO_TO_ENTITY).collect(Collectors.toList());
	}

	@Override
	public RoleDTO CONVERT_FROM_ENTITY_TO_DTO(RoleEntity roleEntity) {
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setId(roleEntity.getId());
		roleDTO.setName(roleEntity.getName());
		roleDTO.setDescription(roleEntity.getDescription());
		return roleDTO;
	}

	@Override
	public RoleEntity CONVERT_FROM_DTO_TO_ENTITY(RoleDTO roleDTO) {
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setDescription(roleDTO.getDescription());
		roleEntity.setName(roleDTO.getName());
		return roleEntity;
	}



}
