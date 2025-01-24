package com.sp.library.mapper;

import com.sp.library.dao.entity.AccountEntity;
import com.sp.library.dao.repository.AccountRepository;
import com.sp.library.dto.AccountRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AccountMapper {
    public final AccountRepository accountRepository;


    public AccountRequestDto entityToDto(AccountEntity accountEntity){
        var dto = AccountRequestDto.builder()
                .balance(accountEntity.getBalance())
                .accountNumber(accountEntity.getAccountNumber())
                .build();

        return dto;
    };
}
