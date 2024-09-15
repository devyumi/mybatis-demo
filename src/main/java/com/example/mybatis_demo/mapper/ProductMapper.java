package com.example.mybatis_demo.mapper;

import com.example.mybatis_demo.domain.Product;
import com.example.mybatis_demo.dto.RequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductMapper {

    void save(@Param("product") Product product);

    Optional<Product> findById(@Param("productId") Integer productId);

    List<Product> findAll(@Param("requestDto") RequestDto requestDto);

    int count();

    void update(@Param("product") Product product);
}
