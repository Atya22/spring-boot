package com.aytaj.otp.service.impl;


import com.aytaj.otp.client.dto.SmsClient;
import com.aytaj.otp.dao.entity.OtpEntity;
import com.aytaj.otp.dao.repository.OtpRepository;
import com.aytaj.otp.rest.OtpRequest;
import com.aytaj.otp.rest.OtpResponse;
import com.aytaj.otp.service.OtpService;
import com.aytaj.otp.util.enums.OtpStatus;
import com.aytaj.otp.util.helper.OtpGenerator;
import com.aytaj.otp.util.helper.OtpServiceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {
    private final OtpRepository otpRepository;
    private final SmsClient smsClient;
    private final OtpGenerator otpGenerator;
    private final OtpServiceHelper otpServiceHelper;

    @Override
    public OtpResponse sendOtp(OtpRequest otpRequest) {
        if (otpRepository.findByMsisdn(otpRequest.msisdn()).isPresent()) {
            var entity = otpRepository.findByMsisdn(otpRequest.msisdn()).orElseThrow();
            if (entity.getOtpStatus() == OtpStatus.BLOCK) {
                if (entity.getBlockTime().isAfter(LocalDateTime.now())) {
                    return sendOtpResponse(entity);
                } else {
                    return removeFromBlock(entity);
                }
            } else {
                if (entity.getSmsCount() >= 5) {
                    return setBlock(entity);
                } else {
                    return sendOtpResponse(ordinarySendOtp(entity));
                }
            }
        } else {
            return sendOtpResponse(sendOtpFirstTime(otpRequest.msisdn()));
        }
    }


    private OtpResponse removeFromBlock(OtpEntity otpEntity) {
        otpServiceHelper.removeEntity(otpEntity);
        var entity = sendOtpFirstTime(otpEntity.getMsisdn());
        smsClient.smsSender(entity.getMsisdn(), entity.getOtpCode());
        return sendOtpResponse(entity);

    }

    private OtpResponse setBlock(OtpEntity entity) {
        entity.setOtpStatus(OtpStatus.BLOCK);
        entity.setBlockTime(LocalDateTime.now().plusMinutes(5));
        entity.setExpirationTime(null);
        otpRepository.save(entity);
        return sendOtpResponse(entity);
    }

    private OtpEntity sendOtpFirstTime(String msisdn) {
        var otpCode = otpGenerator.generate();
        var entity = OtpEntity.builder()
                .msisdn(msisdn)
                .otpCode(otpCode)
                .SmsCount(1)
                .otpStatus(OtpStatus.PENDING)
                .expirationTime(LocalDateTime.now().plusMinutes(5))
                .verifyCount(0)
                .build();

        otpRepository.save(entity);

        smsClient.smsSender(msisdn, entity.getOtpCode());
        return entity;
    }

    private OtpEntity ordinarySendOtp(OtpEntity entity) {
        entity.setSmsCount(entity.getSmsCount() + 1);
        entity.setOtpStatus(OtpStatus.PENDING);
        entity.setOtpCode(otpGenerator.generate());
        entity.setExpirationTime(LocalDateTime.now().plusMinutes(5));

        otpRepository.save(entity);
        smsClient.smsSender(entity.getMsisdn(), entity.getOtpCode());

        return entity;

    }

    private OtpResponse sendOtpResponse(OtpEntity entity) {
        return new OtpResponse(entity.getOtpStatus(), entity.getExpirationTime(), entity.getBlockTime());
    }
}
