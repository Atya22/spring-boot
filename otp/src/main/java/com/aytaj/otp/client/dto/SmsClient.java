package com.aytaj.otp.client.dto;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SmsClient {

    public void smsSender(String msisdn, String code) {
        log.info("Otp code: {}, msisdn: {}", code, msisdn);
    }
}
