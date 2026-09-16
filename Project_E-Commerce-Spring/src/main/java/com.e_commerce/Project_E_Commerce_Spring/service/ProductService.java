package com.e_commerce.Project_E_Commerce_Spring.service;

import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Product;
import com.e_commerce.Project_E_Commerce_Spring.Repository.product_module.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {



    private  final ProductRepository productRepository;

    public Product findById(Long productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new NoSuchElementException("Product not found with this id"));
        return product;
    }
}
