package com.aytaj.otp.util.helper;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class OtpGenerator {

    public String generate() {
        var random = new SecureRandom();
        int otp = random.nextInt(1000000);
        return String.format("%06d", otp);
    }
}
