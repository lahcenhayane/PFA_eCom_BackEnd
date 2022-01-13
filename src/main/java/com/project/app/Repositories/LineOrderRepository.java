package com.project.app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.app.Entities.LineOrderEntity;


@Repository
public interface LineOrderRepository extends JpaRepository<LineOrderEntity, Long>{

}
