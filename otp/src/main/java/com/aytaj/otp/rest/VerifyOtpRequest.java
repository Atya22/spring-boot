package com.aytaj.otp.rest;

public record VerifyOtpRequest(String msisdn, String otpCode) {
}
