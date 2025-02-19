package com.aytaj.otp.service;

import com.aytaj.otp.rest.OtpRequest;
import com.aytaj.otp.rest.OtpResponse;

public interface OtpService {
    OtpResponse sendOtp(OtpRequest otpRequest);
}
