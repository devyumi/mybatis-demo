package com.example.mybatis_demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String role;
}
