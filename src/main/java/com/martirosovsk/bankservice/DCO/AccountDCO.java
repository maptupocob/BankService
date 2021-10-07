package com.martirosovsk.bankservice.DCO;

import com.martirosovsk.bankservice.DTO.AccountDTO;
import com.martirosovsk.bankservice.domain.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDCO {
    public AccountDTO accountToDTO(Account acc){
        return new AccountDTO(acc.getId(), acc.getNumber(), acc.getBalance(),acc.getCurrency());
    }
}
