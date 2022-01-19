package com.project.app.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.app.Entities.OrderEntity;


@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>{

	//SELECT u FROM User u JOIN u.roles r WHERE r.name LIKE '%role name%'
	@Query(value = "SELECT * FROM users u, orders o WHERE o.user_id = u.id AND u.id = ?1", nativeQuery = true)
	Page<OrderEntity> getOrderByUserID(Long id, PageRequest of);

//	Page<OrderEntity> findByUserId(Long id, PageRequest of);
//	
//	Page<OrderEntity> findOrderEntityByUserEntityId(Long id, PageRequest of);

	
}
