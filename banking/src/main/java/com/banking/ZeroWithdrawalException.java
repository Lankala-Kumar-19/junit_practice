package com.banking;

public class ZeroWithdrawalException extends RuntimeException{
    public ZeroWithdrawalException(String msg){
        super(msg);
    }
}
