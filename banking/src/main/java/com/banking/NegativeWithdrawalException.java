package com.banking;

public class NegativeWithdrawalException extends RuntimeException{
    public NegativeWithdrawalException(String msg){
        super(msg);
    }
}
