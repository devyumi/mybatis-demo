package com.example.mybatis_demo.service;

import com.example.mybatis_demo.domain.Member;
import com.example.mybatis_demo.dto.JoinDto;
import com.example.mybatis_demo.mapper.MemberMapper;
import com.example.mybatis_demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void join(JoinDto joinDto) {
        checkDuplicationMember(joinDto);
        checkPassword(joinDto);

        memberRepository.save(Member.builder()
                .name(joinDto.getName())
                .email(joinDto.getEmail())
                .password(passwordEncoder.encode(joinDto.getPassword()))
                .phone(joinDto.getPhone())
                .role("ROLE_USER")
                .regDate(LocalDateTime.now())
                .build());
    }

    @Transactional(readOnly = true)
    public Member findMember(String username) {
        return memberRepository.findByEmail(username).orElseThrow(() -> new IllegalArgumentException("잘못된 이메일 값입니다."));
    }

    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    private void checkDuplicationMember(JoinDto joinDto) {
        memberRepository.findByEmail(joinDto.getEmail())
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
