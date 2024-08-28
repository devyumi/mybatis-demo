package com.example.mybatis_demo.service.annotation;

import com.example.mybatis_demo.dto.JoinDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, JoinDto> {
    @Override
    public boolean isValid(JoinDto joinDto, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

        if (joinDto.getPassword() != null && joinDto.getCheckedPassword() != null) {
            return joinDto.getPassword().equals(joinDto.getCheckedPassword());
        }
        context.buildConstraintViolationWithTemplate("비밀번호가 일치하지 않습니다.").addConstraintViolation();
        return false;
    }
}
