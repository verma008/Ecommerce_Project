package com.springboot.ecommerce.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springboot.ecommerce.JWT.JwtAuthenticationFilter;

@Configuration
public class MyConfig {

	//ModelMapper Java library used for object-to-object mapping. 
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
	
	
	@Autowired
	private JwtAuthenticationFilter filter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
		.cors(cors->cors.disable())
		.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(auth->auth
		.requestMatchers("user/signup").permitAll()
		.requestMatchers("user/login").permitAll()
		.anyRequest().authenticated());
		
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}
