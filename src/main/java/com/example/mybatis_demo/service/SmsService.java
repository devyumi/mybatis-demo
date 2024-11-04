package com.example.mybatis_demo.service;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private final DefaultMessageService messageService;

    @Value("${coolsms.apikey}")
    private String apiKey;

    @Value("${coolsms.apisecret}")
    private String secretKey;

    @Value("${coolsms.fromnumber}")
    private String fromPhoneNumber;

    public SmsService() {
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, secretKey, "https://api.coolsms.co.kr");
    }


    /**
     * 단건 메시지 발송
     * @param phoneNumber
     * @return
     */
    public SingleMessageSentResponse sendMessage(String phoneNumber) {
        Message message = new Message();
        message.setFrom(fromPhoneNumber);
        message.setTo(phoneNumber);
        message.setText("인증번호");

        return this.messageService.sendOne(new SingleMessageSendingRequest(message));
    }
}
