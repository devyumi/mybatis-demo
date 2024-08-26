package com.example.mybatis_demo.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Member {
    private Integer id;

    @NotBlank(message = "회사명은 필수 입력 값입니다.")
    @Size(min = 2, max = 20, message = "회사명은 2자 이상 20자 이자만 가능합니다.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z]+$", message = "한글, 영어만 가능합니다.")
    private String name;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^[0-9]{3}[-]+[0-9]{4}[-]+[0-9]{4}$",
            message = "000-0000-0000 형식이어야 합니다.")
    private String phone;
}
