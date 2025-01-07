package com.springboot.ecommerce.services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.ecommerce.DTO.ApiResponse;
import com.springboot.ecommerce.DTO.UserRequestDTO;
import com.springboot.ecommerce.entities.User;
import com.springboot.ecommerce.exceptions.AlreadyExistException;
import com.springboot.ecommerce.repositories.UserRepository;
import com.springboot.ecommerce.services.UserService;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Override
	public ApiResponse signUpUser(UserRequestDTO userDto) {		
		
		//check user is already exist or not
		User dbUser = this.userRepository.findByEmail(userDto.getEmail());
		ApiResponse apiResponse=new ApiResponse();
		try{
			if(dbUser != null) {
				throw new AlreadyExistException("User already exist. Please create another user");
			}
			
			User user = this.modelMapper.map(userDto, User.class);
			user.setPassword(this.bCryptPasswordEncoder.encode(userDto.getPassword()));
			this.userRepository.save(user);
			apiResponse.setMessage("User Created Successfully");	
	   }catch(Exception e) {
		   e.printStackTrace();
		   apiResponse.setMessage("User already exist. Please create another user");
	   }
		return apiResponse;	
      }
}
