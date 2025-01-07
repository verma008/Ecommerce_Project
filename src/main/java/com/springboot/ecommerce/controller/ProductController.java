package com.springboot.ecommerce.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecommerce.DTO.ApiResponse;
import com.springboot.ecommerce.DTO.ProductRequestDTO;
import com.springboot.ecommerce.DTO.ProductResponseDTO;
import com.springboot.ecommerce.services.ProductService;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController{
	
	@Autowired
	private ProductService productService;
	
	//api to add new product
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductRequestDTO productDto) {
		log.info("Adding a new product: {}", productDto);
		ApiResponse createdProduct = this.productService.createProduct(productDto);
		return new ResponseEntity<ApiResponse>(createdProduct,HttpStatus.CREATED);
	}
	
	//API to retrieve all products
	@GetMapping("/")
	public List<ProductResponseDTO> getAllProducts(){
		List<ProductResponseDTO> allProducts = this.productService.getAllProducts();
		return allProducts;
	}
	
	//Api to  retrieve single product by ID
	@GetMapping("/{id}")
	public ProductResponseDTO getSingleProduct(@PathVariable("id") Integer id) {
		return this.productService.getSingleProduct(id);
	}
	
	
	//Api to update product
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductRequestDTO productDto,@PathVariable("id") Integer id) {
		ApiResponse product = this.productService.updateProduct(productDto,id);
		log.info("Product deleted : {}", product);
		return new ResponseEntity<ApiResponse>(product,HttpStatus.OK);
	}
	
	
	//Api to delete product
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("id") Integer id) {
	  ApiResponse deletedProduct = this.productService.deleteProduct(id);
	  log.info("Product deleted : {}", deletedProduct);
	  return new ResponseEntity<ApiResponse>(deletedProduct,HttpStatus.NOT_FOUND);
	}
}
