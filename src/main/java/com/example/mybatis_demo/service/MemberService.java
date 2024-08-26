package com.example.mybatis_demo.service;

import com.example.mybatis_demo.domain.Member;
import com.example.mybatis_demo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    public void saveMember(Member member) {
        memberMapper.save(member);
    }

    public List<Member> findMembers() {
        return memberMapper.findAll();
    }
}
