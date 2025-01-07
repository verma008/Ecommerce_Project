package com.springboot.ecommerce.services;

import java.util.List;

import com.springboot.ecommerce.DTO.ApiResponse;
import com.springboot.ecommerce.DTO.ProductRequestDTO;
import com.springboot.ecommerce.DTO.ProductResponseDTO;



public interface ProductService {

	public ApiResponse createProduct(ProductRequestDTO productDto);

    List<ProductResponseDTO>  getAllProducts();

	public ProductResponseDTO getSingleProduct(Integer id);

	public ApiResponse updateProduct(ProductRequestDTO productDto,Integer id);

	public ApiResponse deleteProduct(Integer id);
}
