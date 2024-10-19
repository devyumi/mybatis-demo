package com.example.mybatis_demo.repository;

import com.example.mybatis_demo.domain.Product;
import com.example.mybatis_demo.dto.RequestDto;

import java.util.List;

public interface ProductCustomRepository {

    List<Product> findAll(RequestDto requestDto);

    Integer count(RequestDto requestDto);
}
