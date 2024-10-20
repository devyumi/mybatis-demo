package com.example.mybatis_demo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
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

    @Positive
    @Max(value = 1000000)
    private Integer priceFrom;

    @Positive
    @Max(value = 1000000)
    private Integer priceTo;

    @Builder.Default
    private Integer page = 1;

    @Builder.Default
    private Integer size = 10;

    public Integer getOffset() {
        return (page - 1) * size;
    }
}
