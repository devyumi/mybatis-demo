package com.example.mybatis_demo.service;

import com.example.mybatis_demo.domain.Member;
import com.example.mybatis_demo.dto.JoinDto;
import com.example.mybatis_demo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void join(JoinDto joinDto) {
        checkDuplicationMember(joinDto);
        checkPassword(joinDto);

        memberMapper.save(Member.builder()
                .name(joinDto.getName())
                .email(joinDto.getEmail())
                .password(passwordEncoder.encode(joinDto.getPassword()))
                .phone(joinDto.getPhone())
                .role("ROLE_USER")
                .build());
    }

    @Transactional(readOnly = true)
    public Optional<Member> findMember(String username) {
        return memberMapper.findByEmail(username);
    }

    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberMapper.findAll();
    }

    private void checkDuplicationMember(JoinDto joinDto) {
        memberMapper.findByEmail(joinDto.getEmail())
                .ifPresent(m -> {
                    throw new IllegalArgumentException("이미 존재하는 회원입니다.");
                });
    }

    private void checkPassword(JoinDto joinDto) {
        if (!joinDto.getPassword().equals(joinDto.getCheckedPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
