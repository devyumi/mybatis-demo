package com.example.mybatis_demo.controller;

import com.example.mybatis_demo.domain.Member;
import com.example.mybatis_demo.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @GetMapping("members")
    public String findMembers(Model model) {
        List<Member> memberList = memberService.findMembers();
        model.addAttribute("memberList", memberList);
        return "member/member-list";
    }

    @GetMapping("member/write")
    public String registerMember(Model model) {
        model.addAttribute("member", new Member());
        return "member/member-form";
    }

    @PostMapping("member/write")
    public String registerMember(@ModelAttribute @Valid Member member, BindingResult result) {
        if (result.hasErrors()) {
            printErrorLog(result);
            return "member/member-form";
        }
        memberService.saveMember(member);
        log.info("회원등록 완료");
        return "redirect:/members";
    }

    private static void printErrorLog(BindingResult result) {
        log.info("{}", "*".repeat(20));
        for (FieldError fieldError : result.getFieldErrors()) {
            log.error("{}: {}", fieldError.getField(), fieldError.getDefaultMessage());
        }
        log.info("{}", "*".repeat(20));
    }
}
