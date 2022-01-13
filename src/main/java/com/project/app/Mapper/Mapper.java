package com.project.app.Mapper;

public interface Mapper<E, D> {

	public D CONVERT_FROM_ENTITY_TO_DTO(E e);
	public E CONVERT_FROM_DTO_TO_ENTITY(D d);

}
