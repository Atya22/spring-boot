package com.aytaj.otp.rest;

import com.aytaj.otp.service.OtpService;
import com.aytaj.otp.service.VerifyOtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/otp")
public class OtpController {
private final OtpService otpService;
private final VerifyOtpService verifyOtpService;

    @PostMapping
    public OtpResponse sendOtp(@RequestBody OtpRequest otpRequest){
        return otpService.sendOtp(otpRequest);
    }

    @PostMapping("verify")
    public OtpResponse verifyOtp(@RequestBody VerifyOtpRequest verifyOtpRequest){
        return verifyOtpService.verifyOtp(verifyOtpRequest);
    }
}
