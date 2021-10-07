package com.martirosovsk.bankservice.domain;

import com.martirosovsk.bankservice.domain.Enums.Currency;
import lombok.Data;

import java.util.Set;

@Data
public class Account {
    int id;
    private int clientID;
    private Set<Integer> cardIDs;
    private Currency currency;
    private int number;
    private long balance;
}
