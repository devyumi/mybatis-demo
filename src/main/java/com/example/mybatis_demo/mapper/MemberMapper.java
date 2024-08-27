package com.example.mybatis_demo.mapper;

import com.example.mybatis_demo.domain.Member;
import com.example.mybatis_demo.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    void save(@Param("member") Member member);

    List<Member> findAll();

    Optional<Member> findByEmail(@Param("email") String email);
}
