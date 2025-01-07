package com.springboot.ecommerce.JWT;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springboot.ecommerce.config.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired 
	private JwtHelper jwtHelper;
	
//	private Logger logger=LoggerFactory.getLogger(OncePerRequestFilter.class);

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader("Authorization");
		
		String username=null;
		String token=null;
		
		if(header != null && header.startsWith("Bearer")) {
		token = header.substring(7);
		
			try {
				username = this.jwtHelper.getUsernameFromToken(token);
				
			}catch(IllegalArgumentException e) {
				log.info("Illegal argument while fetching the username from token !!");
				e.printStackTrace();
			}catch(ExpiredJwtException e) {
				log.info("Jwt token has been expired !!");
				e.printStackTrace();
			}catch(MalformedJwtException e) {
				log.info("some unwanted changes has done in token !!");
			}catch(Exception e) {
				e.printStackTrace();
			}			
		} 
		else{
			log.info("Invalid Header Value !!");
		}
		
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			Boolean validateToken=this.jwtHelper.validateToken(token, userDetails);
			if(validateToken){
				//set the authentication
				UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}else {
				log.info("Token validation fails");
			}
		}
		filterChain.doFilter(request, response);
	}
} 
