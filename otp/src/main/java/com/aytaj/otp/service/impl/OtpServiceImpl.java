package com.aytaj.otp.service.impl;


import com.aytaj.otp.client.dto.SmsClient;
import com.aytaj.otp.dao.entity.OtpEntity;
import com.aytaj.otp.dao.repository.OtpRepository;
import com.aytaj.otp.rest.SendOtpRequest;
import com.aytaj.otp.rest.SendOtpResponse;
import com.aytaj.otp.service.OtpService;
import com.aytaj.otp.util.enums.OtpStatus;
import com.aytaj.otp.util.helper.OtpGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final OtpRepository otpRepository;
    private final OtpGenerator otpGenerator;
    private final SmsClient smsClient;

    @Override
    public SendOtpResponse sendOtp(SendOtpRequest sendOtpRequest) {
        var otpEntity = otpRepository.findByMsisdn(sendOtpRequest.msisdn());

        if (otpEntity.isPresent()) {
            var otpData = otpEntity.get();
            if (otpData.getOtpStatus().equals(OtpStatus.BLOCK)) {
                if (otpData.getBlockTime().isAfter(LocalDateTime.now())) {
                    return sendOtpResponse(otpData);
                } else {
//                    remove, send otp again
                    removeData(sendOtpRequest.msisdn());
                    var entity = sendOtpFirstTime(sendOtpRequest.msisdn());
                    smsClient.smsSender(sendOtpRequest.msisdn(), entity.getOtpCode());
                    return sendOtpResponse(entity);
                }
            } else {
                if (otpData.getSmsCount() >= 5) {
                    otpData.setOtpStatus(OtpStatus.BLOCK);
                    otpData.setBlockTime(LocalDateTime.now().plusMinutes(5));
                    var enity = otpRepository.save(otpData);
                    return sendOtpResponse(enity);
                } else {
//                    send sms, increase sendCounter, set ResponseStatus to Pending
                    otpData.setSmsCount(otpData.getSmsCount() + 1);
                    otpData.setOtpStatus(OtpStatus.PENDING);
                    otpData.setOtpCode(otpGenerator.generate());
                    var entity = otpRepository.save(otpData);
                    smsClient.smsSender(sendOtpRequest.msisdn(), entity.getOtpCode());
                    return sendOtpResponse(entity);
                }

            }
        } else {
//          first otp
            var enity = sendOtpFirstTime(sendOtpRequest.msisdn());
            smsClient.smsSender(sendOtpRequest.msisdn(), enity.getOtpCode());
            return sendOtpResponse(enity);
        }
    }

    private OtpEntity sendOtpFirstTime(String msisdin) {
        var code = otpGenerator.generate();
        var entity = OtpEntity.builder()
                .otpCode(code)
                .msisdn(msisdin)
                .otpStatus(OtpStatus.PENDING)
                .SmsCount(1)
                .expirationTime(LocalDateTime.now().plusMinutes(5))
                .build();
        return otpRepository.save(entity);
    }

    private SendOtpResponse sendOtpResponse(OtpEntity entity) {
        return new SendOtpResponse(entity.getOtpStatus(), entity.getExpirationTime(), entity.getBlockTime());
    }

    private void removeData(String msisdn) {
        var entity = otpRepository.findByMsisdn(msisdn).orElseThrow();
        otpRepository.delete(entity);
    }

}
