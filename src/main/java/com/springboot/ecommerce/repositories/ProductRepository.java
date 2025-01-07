package com.springboot.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ecommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
