package com.sp.library.service;

import com.sp.library.dao.entity.AccountEntity;
import com.sp.library.dao.repository.AccountRepository;
import com.sp.library.dto.AccountRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    public void addAccount(AccountRequestDto dto){
        try {
            var entity = AccountEntity.builder()
                    .accountNumber(dto.getAccountNumber())
                    .balance(dto.getBalance())
                    .build();
            accountRepository.save(entity);
            log.info("Account created successfully: {}", dto.getAccountNumber());
        }catch (Exception e){
            log.error("Account creation error: {}", dto);
        }
    }
}
