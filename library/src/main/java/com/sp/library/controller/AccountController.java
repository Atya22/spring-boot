package com.sp.library.controller;

import com.sp.library.dao.entity.AccountEntity;
import com.sp.library.dao.repository.AccountRepository;
import com.sp.library.dto.AccountRequestDto;
import com.sp.library.mapper.AccountMapper;
import com.sp.library.service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.awt.image.VolatileImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
//@AllArgsConstructor
@RestController
@RequestMapping("api/v1/account")
public class AccountController {
    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;


@PostMapping
    public void addAccount (@Valid @RequestBody AccountRequestDto dto){
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

    @GetMapping("select")
    public AccountRequestDto getAccountSelect(@RequestParam(value = "balance", required = false) Double balance){
         return accountMapper.entityToDto(accountRepository.findByBalance(balance).get());
    }

    @PatchMapping("update")
    public void updateAccountByNumber (@RequestParam(value = "id", required = false) Long id,
                                       @RequestParam(value = "balance", required = false) Double balance){
        accountRepository.updateAccountByNumber(id, balance);

    }
}
