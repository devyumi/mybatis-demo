package com.example.mybatis_demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private Integer id;
    private String name;

    @NumberFormat(pattern = "#,###원")
    private Integer price;

    @NumberFormat(pattern = "#,###개")
    private Integer quantity;

    private String manufacturer;
    private Member member;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
