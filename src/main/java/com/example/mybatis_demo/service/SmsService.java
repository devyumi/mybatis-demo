package com.example.mybatis_demo.service;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
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

    /**
     * 단건 메시지 발송
     *
     * @param phoneNumber
     */
    public void sendMessage(String phoneNumber) {
        DefaultMessageService messageService = NurigoApp.INSTANCE.initialize(apiKey, secretKey, "https://api.coolsms.co.kr");
        Message message = new Message();
        message.setFrom(fromPhoneNumber);
        message.setTo(phoneNumber);
        message.setText("인증번호");

        try {
            messageService.send(message);
        } catch (NurigoMessageNotReceivedException exception) {
            System.out.println(exception.getFailedMessageList());
            System.out.println(exception.getMessage());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
