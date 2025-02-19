package com.aytaj.otp.service.impl;

import com.aytaj.otp.dao.repository.OtpRepository;
import com.aytaj.otp.exception.NotFoundException;
import com.aytaj.otp.rest.OtpResponse;
import com.aytaj.otp.rest.VerifyOtpRequest;
import com.aytaj.otp.service.VerifyOtpService;
import com.aytaj.otp.util.enums.OtpStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class VerifyOtpServiceImpl implements VerifyOtpService {
    private final OtpRepository otpRepository;


    @Override
    public OtpResponse verifyOtp(VerifyOtpRequest verifyOtpRequest) throws NotFoundException {
        var entity = otpRepository.findByMsisdn(verifyOtpRequest.msisdn()).orElseThrow(
                () -> new NotFoundException("OTP record not found for MSISDN: " + verifyOtpRequest.msisdn())
        );
        if (entity.getExpirationTime().isBefore(LocalDateTime.now())) {
            return new OtpResponse(OtpStatus.EXPIRED, null, null);
        }
        if (entity.getOtpStatus() == OtpStatus.BLOCK) {
            if (entity.getBlockTime().isAfter(LocalDateTime.now())) {
                return new OtpResponse(OtpStatus.BLOCK, null, null);
            } else {
                otpRepository.delete(entity);
                return new OtpResponse(OtpStatus.EXPIRED, null, null);
            }
        } else {
            if (Objects.equals(entity.getOtpCode(), verifyOtpRequest.otpCode())) {
                otpRepository.delete(entity);
                return new OtpResponse(OtpStatus.SUCCESS, null, null);
            } else {
                var blockTime = LocalDateTime.now().plusMinutes(5);
                entity.setVerifyCount(entity.getVerifyCount() + 1);

                if (entity.getVerifyCount() >= 5) {
                    entity.setOtpStatus(OtpStatus.BLOCK);
                    entity.setBlockTime(blockTime);
                    entity.setExpirationTime(null);
                    otpRepository.save(entity);
                    return new OtpResponse(OtpStatus.BLOCK, null, null);
                } else {
                    entity.setOtpStatus(OtpStatus.FAIL);
                    otpRepository.save(entity);
                    return new OtpResponse(OtpStatus.FAIL, null, null);
                }
            }
        }
    }
}
