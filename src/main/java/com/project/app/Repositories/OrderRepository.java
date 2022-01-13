package com.project.app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.app.Entities.OrderEntity;


@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>{

}
