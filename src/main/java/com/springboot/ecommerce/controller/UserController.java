package com.springboot.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecommerce.DTO.ApiResponse;
import com.springboot.ecommerce.DTO.LoginRequestDTO;
import com.springboot.ecommerce.DTO.LoginResponseDTO;
import com.springboot.ecommerce.DTO.UserRequestDTO;
import com.springboot.ecommerce.JWT.JwtHelper;
import com.springboot.ecommerce.config.UserDetailsServiceImpl;
import com.springboot.ecommerce.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController{
	
	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
    private UserDetailsServiceImpl userDetailsService;
	@Autowired
	private JwtHelper jwtHelper;
	
    //API to register user
    @PostMapping("/signup")
	public ResponseEntity<ApiResponse> signUp(@RequestBody UserRequestDTO userDto){
		ApiResponse registeredUser = this.userService.signUpUser(userDto);
		return new ResponseEntity<ApiResponse>(registeredUser, HttpStatus.CREATED);
	}
	
    
    //login api
    @PostMapping(value = "/login", produces = "application/json")
    public LoginResponseDTO createToken(@RequestBody LoginRequestDTO loginDto){
    	
    	LoginResponseDTO loginResponse=new LoginResponseDTO();
    	
    	//edge cases to check email and password should not be null or empty
    	if(loginDto.getEmail()==null || loginDto.getEmail().isEmpty()
    		|| loginDto.getPassword()==null || loginDto.getPassword().isEmpty()) {
    		loginResponse.setToken("Email or password cannot be empty.");
    		return loginResponse;
    	}
    	
    	
    	UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
    	try {
    	this.authenticationManager.authenticate(authentication);
    	UserDetails userDetails = this.userDetailsService.loadUserByUsername(loginDto.getEmail());
    	String token = this.jwtHelper.generateToken(userDetails);
    	loginResponse.setToken(token);
    	}catch(BadCredentialsException e){
            e.printStackTrace();
			loginResponse.setToken("Invalid username and password.Please try Again.");
		}
		catch (Exception e){
		        e.printStackTrace();
		        loginResponse.setToken("An unexpected error occurred. Please try again later.");
		    }
    	return loginResponse;
    }
}
