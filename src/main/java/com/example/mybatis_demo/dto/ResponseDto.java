package com.example.mybatis_demo.dto;

import com.example.mybatis_demo.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private Integer page;
    private Integer size;
    private String type;
    private String keyword;
    private Integer priceFrom;
    private Integer priceTo;
    private Integer total;
    private Integer last;
    private Integer start;
    private Integer end;
    private List<Product> products;

    @Builder
    public ResponseDto(RequestDto requestDto, List<Product> products, Integer total) {
        this.page = requestDto.getPage();
        this.size = requestDto.getSize();
        this.type = requestDto.getType();
        this.keyword = requestDto.getKeyword();
        this.priceFrom = requestDto.getPriceFrom();
        this.priceTo = requestDto.getPriceTo();
        this.products = products;
        this.total = total;
        this.last = (int) Math.ceil(total / (double) size);
        this.start = (page - 1) / 10 * 10 + 1;
        this.end = (last == 0) ? 1 : Math.min(start + 9, last);
    }
}
