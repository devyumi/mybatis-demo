package com.example.mybatis_demo.controller;

import com.example.mybatis_demo.domain.Product;
import com.example.mybatis_demo.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

    @GetMapping("products")
    public String findProducts(Model model) {
        List<Product> productList = productService.findProducts();
        model.addAttribute("productList", productList);
        return "product/product-list";
    }

    @GetMapping("product/write")
    public String registerProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product/product-form";
    }

    @PostMapping("product/write")
    public String registerProduct(@ModelAttribute @Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            printErrorLog(result);
            return "product/product-form";
        }
        productService.saveProduct(product);
        log.info("상품등록 완료");
        return "redirect:/products";
    }

    @GetMapping("product/edit/{productId}")
    public String editProduct(@PathVariable(value = "productId") Integer productId, Model model) {
        model.addAttribute("product", productService.findOne(productId));
        return "product/product-edit";
    }

    @PostMapping("product/edit")
    public String updateProduct(@ModelAttribute @Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            printErrorLog(result);
            return "product/product-edit";
        }
        productService.updateProduct(product);
        log.info("상품수정 완료");
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
