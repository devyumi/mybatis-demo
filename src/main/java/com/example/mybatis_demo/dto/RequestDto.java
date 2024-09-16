package com.example.mybatis_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestDto {
    private String type;
    private String keyword;
    private Integer page = 0;
    private Integer size = 10;

    public Integer getOffset() {
        return page * size;
    }
}
