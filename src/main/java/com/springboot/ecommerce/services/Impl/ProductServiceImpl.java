package com.springboot.ecommerce.services.Impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.ecommerce.DTO.ApiResponse;
import com.springboot.ecommerce.DTO.ProductRequestDTO;
import com.springboot.ecommerce.DTO.ProductResponseDTO;
import com.springboot.ecommerce.entities.Product;
import com.springboot.ecommerce.exceptions.ResourceNotFoundException;
import com.springboot.ecommerce.repositories.ProductRepository;
import com.springboot.ecommerce.services.ProductService;


@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public ApiResponse createProduct(ProductRequestDTO productDto) {
		Product product = this.modelMapper.map(productDto, Product.class);
		this.productRepository.save(product);
		return new ApiResponse("Product Created successfully");
	}

	
	
	@Override
	public List<ProductResponseDTO> getAllProducts() {
		
		List<Product> products = this.productRepository.findAll();
		List<ProductResponseDTO> productResponse = new ArrayList<>();
		
		for (Product product : products) {
			ProductResponseDTO map = this.modelMapper.map(product, ProductResponseDTO.class);
			productResponse.add(map);
		}
		return productResponse;
	}

	
	
	@Override
	public ProductResponseDTO getSingleProduct(Integer id) {
		//first check if product id exists or not
		Product product = this.productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product "," Id", id));
		return this.modelMapper.map(product, ProductResponseDTO.class);
	}

	
	
	@Override
	public ApiResponse updateProduct(ProductRequestDTO productDto, Integer id) {
		//check product id exists or not
		Product product=this.productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product "," Id", id));
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setImage(productDto.getImage());
		this.productRepository.save(product);
		return new ApiResponse("Product has been updated successfully");
	}
	

	
	@Override
	public ApiResponse deleteProduct(Integer id) {	
		//check product id exists or not
		Product product = this.productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product "," Id", id));
		this.productRepository.delete(product);
		return new ApiResponse("Product has been deleted successfully");
	}

}

