package com.aytaj.otp.dao.entity;

import com.aytaj.otp.util.enums.OtpStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "otp")
public class OtpEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String otpCode;
    private String msisdn;
    private Integer SmsCount;

    /**
     * The expiration time of the OTP after which it is no longer valid.
     */
    private LocalDateTime expirationTime;
    private OtpStatus otpStatus;

    /**
     * The time when the user is blocked from requesting a new OTP due to multiple failed attempts.
     */
    private LocalDateTime blockTime;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
