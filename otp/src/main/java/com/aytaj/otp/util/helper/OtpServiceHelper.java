package com.aytaj.otp.util.helper;

import com.aytaj.otp.dao.entity.OtpEntity;
import com.aytaj.otp.dao.repository.OtpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OtpServiceHelper {
    private final OtpRepository otpRepository;

    public void removeEntity(OtpEntity entity){
        otpRepository.deleteById(entity.getId());
    }
}
