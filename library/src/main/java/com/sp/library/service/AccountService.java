package com.sp.library.service;

import com.sp.library.dao.entity.AccountEntity;
import com.sp.library.dao.repository.AccountRepository;
import com.sp.library.dto.AccountRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public void addAccount(AccountRequestDto dto){
        var entity = AccountEntity.builder()
                .accountNumber(dto.getAccountNumber())
                .balance(dto.getBalance())
                .build();
        accountRepository.save(entity);
    }
}
