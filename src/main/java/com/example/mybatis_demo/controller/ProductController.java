package com.example.mybatis_demo.controller;

import com.example.mybatis_demo.config.auth.CustomUserDetails;
import com.example.mybatis_demo.dto.ProductSaveDto;
import com.example.mybatis_demo.dto.ProductUpdateDto;
import com.example.mybatis_demo.dto.RequestDto;
import com.example.mybatis_demo.service.MemberService;
import com.example.mybatis_demo.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;
    private final MemberService memberService;

    @GetMapping
    public String findProducts(RequestDto requestDto, Model model) {
        model.addAttribute("productList", productService.findProducts(requestDto));
        return "product/product-list";
    }

    @GetMapping("save")
    public String registerProduct(Model model) {
        model.addAttribute("productSaveDto", new ProductSaveDto());
        return "product/product-form";
    }

    @PostMapping("save")
    public String registerProduct(@AuthenticationPrincipal CustomUserDetails auth,
                                  @ModelAttribute @Valid ProductSaveDto productSaveDto, BindingResult result) {
        if (result.hasErrors()) {
            printErrorLog(result);
            return "product/product-form";
        }
        productService.saveProduct(productSaveDto, memberService.findMember(auth.getUsername()));
        log.info("상품등록 완료 | 등록자: {}", auth.getName());
        return "redirect:/products";
    }

    @GetMapping("update/{productId}")
    public String editProduct(@PathVariable(value = "productId") Long productId, Model model) {
        model.addAttribute("productUpdateDto", productService.findProduct(productId));
        return "product/product-edit";
    }

    @PostMapping("update")
    public String updateProduct(@AuthenticationPrincipal CustomUserDetails auth,
                                @ModelAttribute @Valid ProductUpdateDto productUpdateDto, BindingResult result) {
        if (result.hasErrors()) {
            printErrorLog(result);
            return "product/product-edit";
        }
        productService.updateProduct(productUpdateDto, memberService.findMember(auth.getUsername()));
        log.info("상품수정 완료 | 수정자: {}", auth.getName());
        return "redirect:/products";
    }

    private static void printErrorLog(BindingResult result) {
        log.info("{}", "*".repeat(20));
        for (FieldError fieldError : result.getFieldErrors()) {
            log.error("{}: {}", fieldError.getField(), fieldError.getDefaultMessage());
        }
        log.info("{}", "*".repeat(20));
    }
}
