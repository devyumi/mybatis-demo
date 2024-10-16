package com.example.mybatis_demo.repository;

import com.example.mybatis_demo.domain.Product;

import java.util.List;

public interface ProductCustomRepository {

    //ResponseDto findAll(RequestDto requestDto);
    List<Product> findAll();
}
