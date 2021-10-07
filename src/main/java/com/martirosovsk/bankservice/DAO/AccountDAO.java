package com.martirosovsk.bankservice.DAO;

import com.martirosovsk.bankservice.domain.Account;
import org.springframework.stereotype.Repository;

import javax.net.ssl.SSLSession;
import java.util.Set;

@Repository
public interface AccountDAO {
    /**
     * search account by account ID
     *
     * @param accountId account ID
     * @return Account
     */
    Account findById(int accountId);

    /**
     * search all accounts owned by client
     *
     * @param clientId client ID
     * @return Account
     */
    Set<Account> findAllByClientId(int clientId);

    /**
     * update account balance
     *
     * @param accountId account ID
     * @param amount    changing amount
     */
    void updateBalance(int accountId, int amount);

    /**
     * search account by unique number;
     * @param number number
     * @return account
     */
    Account findByNumber(int number);
}
