package com.sp.library.controller;

import com.sp.library.dao.entity.AccountEntity;
import com.sp.library.dao.repository.AccountRepository;
import com.sp.library.dto.AccountRequestDto;
import com.sp.library.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
//@AllArgsConstructor
@RestController
@RequestMapping("api/v1/account")
public class AccountController {
    private final AccountService accountService;
    private final AccountRepository accountRepository;


@PostMapping
    public void addAccount (@RequestBody AccountRequestDto dto){
    accountService.addAccount(dto);
}

@GetMapping
  public List <AccountEntity> getAccounts(){
    return accountRepository.findAll();
}
    @GetMapping("{id}")
    public AccountEntity getAccount(@PathVariable("id") Long id){
        return accountRepository.findById(id).get();
    }
}
