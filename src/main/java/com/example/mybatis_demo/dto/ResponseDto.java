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
    private Integer total;
    private Integer end;
    private List<Product> products;

    @Builder
    public ResponseDto(RequestDto requestDto, List<Product> products, Integer total) {
        this.page = requestDto.getPage();
        this.size = requestDto.getSize();
        this.total = total;
        this.products = products;
        this.end = (int) Math.ceil(total/(double)size);
    }
}
