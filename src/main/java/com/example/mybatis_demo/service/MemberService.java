package com.example.mybatis_demo.service;

import com.example.mybatis_demo.domain.Member;
import com.example.mybatis_demo.dto.JoinDto;
import com.example.mybatis_demo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    public void join(JoinDto joinDto) {
        checkDuplicationMember(joinDto);

        memberMapper.save(Member.builder()
                .name(joinDto.getName())
                .email(joinDto.getEmail())
                .password(passwordEncoder.encode(joinDto.getPassword()))
                .phone(joinDto.getPhone())
                .role("ROLE_USER")
                .build());
    }

    public List<Member> findMembers() {
        return memberMapper.findAll();
    }

    private void checkDuplicationMember(JoinDto joinDto) {
        memberMapper.findByEmail(joinDto.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
