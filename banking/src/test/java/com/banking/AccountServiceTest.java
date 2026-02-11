package com.banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountServiceTest {
    private AccountRepository accountRepository;
    private AccountService accountService;
    private Account account;

    @BeforeEach
    void setUp(){

        account = new Account(1L,"kumar",new BigDecimal("1000"));
        accountRepository = mock(AccountRepository.class);
        accountService = new AccountService(accountRepository);
    }

    @Test
    void deposit_happyPath_shouldIncreaseBalance(){

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        accountService.deposit(1L,new BigDecimal("50"));
        assertEquals(0,account.getBalance().compareTo(new BigDecimal("1050")));

    }

    @Test
    void zeroDeposit(){

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        accountService.deposit(1L,new BigDecimal("0"));
        assertEquals(0,account.getBalance().compareTo(new BigDecimal("1000")));

    }

    @Test
    void negativeDeposit(){
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        assertThrows(IllegalArgumentException.class,()->{
            accountService.deposit(1L,new BigDecimal("-10"));
        });
    }

    @Test
    void AccountNotFound(){
        assertThrows(AccountNotFoundException.class,()->{
           accountService.withdraw(1L,new BigDecimal("100"));
        });
    }

    @Test
    void checkWithdraw(){

        Account account = new Account(1L,"kumar",new BigDecimal("1000"));
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        accountService.withdraw(1L,new BigDecimal(1000));
        assertEquals(0,account.getBalance().compareTo(new BigDecimal(0)));

    }





}
