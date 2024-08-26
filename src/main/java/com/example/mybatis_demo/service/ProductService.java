package com.example.mybatis_demo.service;

import com.example.mybatis_demo.domain.Product;
import com.example.mybatis_demo.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;

    public void saveProduct(Product product) {
        productMapper.save(product);
    }

    public List<Product> findProducts(String type, String keyword) {
        return productMapper.findAll(type, keyword);
    }

    public Optional<Product> findOne(Integer productId) {
        return productMapper.findById(productId);
    }

    public void updateProduct(Product product) {
        productMapper.update(product);
    }
}
