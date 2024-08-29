package com.example.mybatis_demo.service;

import com.example.mybatis_demo.domain.Member;
import com.example.mybatis_demo.domain.Product;
import com.example.mybatis_demo.dto.ProductSaveDto;
import com.example.mybatis_demo.dto.ProductUpdateDto;
import com.example.mybatis_demo.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;

    public void saveProduct(ProductSaveDto productSaveDto, Member member) {
        productMapper.save(Product.builder()
                .name(productSaveDto.getName())
                .price(productSaveDto.getPrice())
                .quantity(productSaveDto.getQuantity())
                .manufacturer(productSaveDto.getManufacturer())
                .member(member)
                .build());
    }

    public List<Product> findProducts(String type, String keyword) {
        return productMapper.findAll(type, keyword);
    }

    public Optional<Product> findOne(Integer productId) {
        return productMapper.findById(productId);
    }

    public void updateProduct(ProductUpdateDto productUpdateDto, Member member) {
        productMapper.update(Product.builder()
                .id(productUpdateDto.getId())
                .name(productUpdateDto.getName())
                .price(productUpdateDto.getPrice())
                .quantity(productUpdateDto.getQuantity())
                .manufacturer(productUpdateDto.getManufacturer())
                .member(member)
                .build());
    }
}
