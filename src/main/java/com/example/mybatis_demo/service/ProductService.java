package com.example.mybatis_demo.service;

import com.example.mybatis_demo.domain.Member;
import com.example.mybatis_demo.domain.Product;
import com.example.mybatis_demo.dto.ProductSaveDto;
import com.example.mybatis_demo.dto.ProductUpdateDto;
import com.example.mybatis_demo.dto.RequestDto;
import com.example.mybatis_demo.dto.ResponseDto;
import com.example.mybatis_demo.mapper.ProductMapper;
import com.example.mybatis_demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Transactional
    public void saveProduct(ProductSaveDto productSaveDto, Member member) {
        productRepository.save(Product.builder()
                .name(productSaveDto.getName())
                .price(productSaveDto.getPrice())
                .quantity(productSaveDto.getQuantity())
                .manufacturer(productSaveDto.getManufacturer())
                .member(member)
                .regDate(LocalDateTime.now())
                .build());
    }

    @Transactional(readOnly = true)
    public ResponseDto findProducts(RequestDto requestDto) {
        return ResponseDto.builder()
                .requestDto(requestDto)
                .products(productMapper.findAll(requestDto))
                .total(productMapper.count(requestDto))
                .build();
    }

    @Transactional(readOnly = true)
    public Product findProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("잘못된 id 값입니다."));
    }

    @Transactional
    public void updateProduct(ProductUpdateDto productUpdateDto, Member member) {
        Product product = findProduct(productUpdateDto.getId());
        productRepository.save(Product.builder()
                .id(product.getId())
                .name(productUpdateDto.getName())
                .price(productUpdateDto.getPrice())
                .quantity(productUpdateDto.getQuantity())
                .manufacturer(productUpdateDto.getManufacturer())
                .member(member)
                .regDate(product.getRegDate())
                .modDate(LocalDateTime.now())
                .build());
    }
}
