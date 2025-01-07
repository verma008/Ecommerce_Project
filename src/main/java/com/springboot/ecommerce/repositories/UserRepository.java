package com.springboot.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ecommerce.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByEmail(String email);
}
