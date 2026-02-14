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
    private Account account,account2;

    @BeforeEach
    void setUp(){

        account = new Account(1L,"kumar",new BigDecimal("1000"));
        account2 = new Account(3L,"chinnu",new BigDecimal("1000"));
        accountRepository = mock(AccountRepository.class);
        accountService = new AccountService(accountRepository);
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.findById(3L)).thenReturn(Optional.of(account2));
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
           accountService.withdraw(2L,new BigDecimal("100"));
        });
    }

    @Test
    void checkWithdraw(){
        Account account = new Account(1L,"kumar",new BigDecimal("1000"));
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        accountService.withdraw(1L,new BigDecimal(1000));
        assertEquals(0,account.getBalance().compareTo(new BigDecimal(0)));
    }

    @Test
    void exactBalance(){
//        Account

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        accountService.withdraw(1L,new BigDecimal("1000"));
        assertEquals(0,account.getBalance().compareTo(new BigDecimal("0")));
    }

    @Test
    void insufficientBalance(){

        assertThrows(InsufficientBalanceException.class,()->{
            accountService.withdraw(1L,new BigDecimal("10000"));
        });
    }

    @Test
    void zeroWithdrawal(){

        assertThrows(ZeroWithdrawalException.class,()->{
            accountService.withdraw(1L,new BigDecimal("0"));
        });
    }

    @Test
    void negativeWithdrawal(){

        assertThrows(NegativeWithdrawalException.class,()->{
            accountService.withdraw(1L,new BigDecimal("-10"));
        });
    }

    @Test
    void accountNotFound(){

        assertThrows(AccountNotFoundException.class,()->{
            accountService.withdraw(2L,new BigDecimal("10"));
        });
    }

    @Test
    void transfer_happy(){

        accountService.transfer(1L,3L,new BigDecimal("100"));
        assertEquals(0,account.getBalance().compareTo(new BigDecimal("900")));
        assertEquals(0,account2.getBalance().compareTo(new BigDecimal("1100")));
    }

    @Test
    void transfer_insufficient_balance(){

        assertThrows(InsufficientBalanceException.class,()->{
            accountService.transfer(1L,3L,new BigDecimal("10000"));
        });
    }

    @Test
    void nonexistentFromAccount(){

        assertThrows(AccountNotFoundException.class,()->{
            accountService.transfer(2L,3L,new BigDecimal("100"));
        });
    }

    @Test
    void nonexistentToAccount(){

        assertThrows(AccountNotFoundException.class,()->{
            accountService.transfer(1L,2L,new BigDecimal("100"));
        });
    }

    @Test
    void ZeroOrNegativeTransfer(){

        assertThrows(ZeroOrNegativeTransfer.class,()->{
            accountService.transfer(1L,3L,new BigDecimal("0"));
        });

        assertThrows(ZeroOrNegativeTransfer.class,()->{
            accountService.transfer(1L,3L,new BigDecimal("-10"));
        });
    }

}
