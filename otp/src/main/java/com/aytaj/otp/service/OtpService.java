package com.aytaj.otp.service;

import com.aytaj.otp.rest.SendOtpRequest;
import com.aytaj.otp.rest.SendOtpResponse;

public interface OtpService {
    SendOtpResponse sendOtp(SendOtpRequest sendOtpRequest);
}
