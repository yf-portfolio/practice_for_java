package com.example.ecsitedeveloplearning.ec.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecsitedeveloplearning.ec.shop.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
}