package com.banking;

public class BankAccount {

    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public void deposit(int amount) {
        if(amount<=0) throw new IllegalArgumentException();
        balance += amount;
    }

    public int getBalance() {
        return balance;
    }
}

