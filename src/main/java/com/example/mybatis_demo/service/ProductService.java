package com.example.mybatis_demo.service;

import com.example.mybatis_demo.domain.Member;
import com.example.mybatis_demo.domain.Product;
import com.example.mybatis_demo.dto.ProductSaveDto;
import com.example.mybatis_demo.dto.ProductUpdateDto;
import com.example.mybatis_demo.dto.RequestDto;
import com.example.mybatis_demo.dto.ResponseDto;
import com.example.mybatis_demo.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;

    @Transactional
    public void saveProduct(ProductSaveDto productSaveDto, Member member) {
        productMapper.save(Product.builder()
                .name(productSaveDto.getName())
                .price(productSaveDto.getPrice())
                .quantity(productSaveDto.getQuantity())
                .manufacturer(productSaveDto.getManufacturer())
                .member(member)
                .build());
    }

    @Transactional(readOnly = true)
    public ResponseDto findProducts(RequestDto requestDto) {

        ResponseDto responseDto = ResponseDto.builder()
                .requestDto(requestDto)
                .products(productMapper.findAll(requestDto))
                .total(productMapper.count())
                .build();

        log.info("total {} ", responseDto.getTotal());
        log.info("end: {} ", responseDto.getEnd());
        return  responseDto;
    }

    @Transactional(readOnly = true)
    public Optional<Product> findOne(Integer productId) {
        return productMapper.findById(productId);
    }

    @Transactional
    public void updateProduct(ProductUpdateDto productUpdateDto, Member member) {
        productMapper.update(Product.builder()
                .id(productUpdateDto.getId())
                .name(productUpdateDto.getName())
                .price(productUpdateDto.getPrice())
                .quantity(productUpdateDto.getQuantity())
                .manufacturer(productUpdateDto.getManufacturer())
                .member(member)
                .modDate(LocalDateTime.now())
                .build());
    }
}
