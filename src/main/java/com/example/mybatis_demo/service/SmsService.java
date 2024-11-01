package com.example.mybatis_demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${coolsms.apikey}")
    private String apiKey;

    @Value("${coolsms.apisecret}")
    private String secretKey;

    @Value("${coolsms.fromnumber}")
    private String fromPhoneNumber;
}
