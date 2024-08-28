package com.example.mybatis_demo.dto;

import com.example.mybatis_demo.service.annotation.PasswordMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@PasswordMatch
public class JoinDto {
    private Integer id;

    @NotBlank(message = "회사명은 필수 입력 값입니다.")
    @Size(min = 2, max = 20, message = "회사명은 2자 이상 20자 이자만 가능합니다.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z]+$", message = "한글, 영어만 가능합니다.")
    private String name;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문, 숫자, 특수기호가 적어도 1개 이상씩 포함된 8~20자만 가능합니다.")
    private String password;

    @NotBlank(message = "비밀번호 확인란은 필수 입력 값입니다.")
    private String checkedPassword;

    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^[0-9]{3}[-]+[0-9]{4}[-]+[0-9]{4}$",
            message = "000-0000-0000 형식이어야 합니다.")
    private String phone;
}
