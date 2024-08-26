package com.example.mybatis_demo.mapper;

import com.example.mybatis_demo.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {

    void save(@Param("member") Member member);

    List<Member> findAll();
}
