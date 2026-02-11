package com.banking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    @Test
    void checkDeposit(){
        BankAccount bankAccount = new BankAccount(1000);

        bankAccount.deposit(100);

        assertEquals(1100,bankAccount.getBalance());
    }

    @Test
    void shouldThrowExceptionIfDepositIsNegative(){
        BankAccount bankAccount = new BankAccount(100);

        assertThrows(IllegalArgumentException.class,()->{
            bankAccount.deposit(-10);
        });
    }
}
