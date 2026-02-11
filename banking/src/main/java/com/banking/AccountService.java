package com.banking;

import java.math.BigDecimal;

public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account deposit(Long accountId, BigDecimal amount) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

//        System.out.println(account.getBalance()+" "+amount);

        if(amount.compareTo(new BigDecimal("0"))<0) throw new IllegalArgumentException();

        account.setBalance(account.getBalance().add(amount));

        return accountRepository.save(account);
    }

    public Account withdraw(Long accountId, BigDecimal amount) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient funds");
        }

        account.setBalance(account.getBalance().subtract(amount));

        return accountRepository.save(account);
    }

    public void transfer(Long fromId, Long toId, BigDecimal amount) {

        Account from = withdraw(fromId, amount);
        deposit(toId, amount);
    }
}

