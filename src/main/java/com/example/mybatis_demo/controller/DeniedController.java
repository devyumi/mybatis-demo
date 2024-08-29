package com.example.mybatis_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeniedController {

    @GetMapping("denied")
    public String denied() {
        return "error/denied";
    }
}
