package com.aytaj.otp.service;

import com.aytaj.otp.rest.OtpRequest;
import com.aytaj.otp.rest.OtpResponse;
import com.aytaj.otp.rest.VerifyOtpRequest;

public interface VerifyOtpService {
    OtpResponse verifyOtp(VerifyOtpRequest verifyOtpRequest);
}
