package com.banking;

public class ZeroOrNegativeTransfer extends RuntimeException{
    public ZeroOrNegativeTransfer(String msg){
        super(msg);
    }
}
