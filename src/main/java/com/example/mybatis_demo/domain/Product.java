package com.example.mybatis_demo.domain;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

@Data
@NoArgsConstructor
public class Product {
    private Integer id;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    @Size(min = 2, max = 20, message = "상품명은 2자 이상 20자 이하만 가능합니다.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z]+$", message = "한글, 영어만 가능합니다.")
    private String name;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    @Positive(message = "양수만 가능합니다.")
    @Max(value = 1000000, message = "1,000,000원 이하만 가능합니다.")
    @NumberFormat(pattern = "#,###원")
    private Integer price;

    @NotNull(message = "수량은 필수 입력 값입니다.")
    @Positive(message = "양수만 가능합니다.")
    @Max(value = 1000, message = "1,000개 이하만 가능합니다.")
    @NumberFormat(pattern = "#,###개")
    private Integer quantity;

    @NotBlank(message = "제조사는 필수 입력 값입니다.")
    @Size(min = 2, max = 20, message = "제조사는 2자 이상 20자 이하만 가능합니다.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z]+$", message = "한글, 영어만 가능합니다.")
    private String manufacturer;
}
