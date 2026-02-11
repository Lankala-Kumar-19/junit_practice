package com.banking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Account {

    private Long id;
    private String ownerName;
    private BigDecimal balance;

    public Account(Long id,String ownerName, BigDecimal balance) {
        this.id=id;
        this.balance = balance;
        this.ownerName = ownerName;
    }
}
