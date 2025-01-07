package com.springboot.ecommerce.services;

import com.springboot.ecommerce.DTO.ApiResponse;
import com.springboot.ecommerce.DTO.UserRequestDTO;

public interface UserService {
	
	ApiResponse signUpUser(UserRequestDTO userDto);
}
