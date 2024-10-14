package com.example.mybatis_demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String role;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
