package com.martirosovsk.bankservice.service;

import com.martirosovsk.bankservice.DTO.AccountDTO;
import com.martirosovsk.bankservice.DTO.BalanceChangeDTO;

public interface AccountService {
    /**
     * updating account balance
     *
     * @param balanceChangeDTO balanceChangeDTO with account number and amount of change
     * @return account DTO with changed balance
     */
    AccountDTO updateBalance(BalanceChangeDTO balanceChangeDTO);

    /**
     * search account by account ID
     *
     * @param accountId account ID
     * @return Account
     */
    AccountDTO findById(int accountId);

    /**
     * get account's balance by account ID
     * @param accountId account ID
     * @return balance in long
     */
    long getBalanceByAccountId(int accountId);
}
