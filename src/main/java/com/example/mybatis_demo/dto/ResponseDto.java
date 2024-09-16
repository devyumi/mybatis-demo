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
    private Integer last;
    private Integer start;
    private Integer end;
    private List<Product> products;

    @Builder
    public ResponseDto(RequestDto requestDto, List<Product> products, Integer total) {
        this.page = requestDto.getPage();
        this.size = requestDto.getSize();
        this.products = products;
        this.total = total;
        this.last = (int) Math.ceil(total/(double)size);
        this.start = page / 10 * 10 + 1;
        this.end = Math.min(start + 9, last);
    }
}
