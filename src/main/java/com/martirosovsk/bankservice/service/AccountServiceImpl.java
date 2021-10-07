package com.martirosovsk.bankservice.service;

import com.martirosovsk.bankservice.DAO.AccountDAO;
import com.martirosovsk.bankservice.DCO.AccountDCO;
import com.martirosovsk.bankservice.DTO.AccountDTO;
import com.martirosovsk.bankservice.domain.Account;
import lombok.Data;
import org.springframework.stereotype.Service;


@Data
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountDAO accDAO;
    private final AccountDCO accDCO;

    @Override
    public AccountDTO updateBalance(int accountId, int amount) {
        accDAO.updateBalance(accountId, amount);
        return accDCO.accountToDTO(accDAO.findById(accountId));
    }

    @Override
    public AccountDTO findById(int accountId) {
        return accDCO.accountToDTO(accDAO.findById(accountId));
    }

    @Override
    public long getBalanceByAccountId(int accountId) {
        Account acc = accDAO.findById(accountId);
        return acc.getBalance();
    }
}
