package com.aytaj.otp.client.dto;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SmsClient {
    public void smsSender(String msisdn, String otp){
        log.info("Otp code: {}, msisdn: {}", msisdn, otp);
    }
}
