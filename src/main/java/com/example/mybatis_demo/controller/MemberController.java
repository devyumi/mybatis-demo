package com.example.mybatis_demo.controller;

import com.example.mybatis_demo.domain.Member;
import com.example.mybatis_demo.dto.JoinDto;
import com.example.mybatis_demo.dto.LoginDto;
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
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("join")
    public String join(Model model) {
        model.addAttribute("joinDto", new JoinDto());
        return "member/join-form";
    }

    @PostMapping("join")
    public String join(@ModelAttribute @Valid JoinDto joinDto, BindingResult result) {
        if (result.hasErrors()) {
            printErrorLog(result);
            return "member/join-form";
        }
        memberService.join(joinDto);
        log.info("회원가입 완료");
        return "redirect:/";
    }

    @GetMapping("login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("loginDto", new LoginDto());
        model.addAttribute("error", error);
        return "member/login-form";
    }

    @PostMapping("logout")
    public String logout() {
        return "redirect:/";
    }

    private static void printErrorLog(BindingResult result) {
        log.info("{}", "*".repeat(20));
        for (FieldError fieldError : result.getFieldErrors()) {
            log.error("{}: {}", fieldError.getField(), fieldError.getDefaultMessage());
        }
        log.info("{}", "*".repeat(20));
    }
}
