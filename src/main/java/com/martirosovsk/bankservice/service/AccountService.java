package com.martirosovsk.bankservice.service;

import com.martirosovsk.bankservice.DTO.AccountDTO;

public interface AccountService {
    /**
     * updating account balance
     *
     * @param accountId account ID
     * @param amount    changing amount
     * @return account DTO with changed balance
     */
    AccountDTO updateBalance(int accountId, int amount);

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
