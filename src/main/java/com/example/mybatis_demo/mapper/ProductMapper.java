package com.example.mybatis_demo.mapper;

import com.example.mybatis_demo.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductMapper {

    void save(@Param("product") Product product);

    List<Product> findAll();

    Optional<Product> findById(@Param("productId") Integer productId);

    void update(@Param("product") Product product);
}
