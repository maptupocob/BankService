package com.martirosovsk.bankservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {
    private  int id;
    private  String name;
    private  Set<Card> cards;
    private  Set<Account> accounts;
}
