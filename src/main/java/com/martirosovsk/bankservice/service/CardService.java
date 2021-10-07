package com.martirosovsk.bankservice.service;

import com.martirosovsk.bankservice.DTO.AccountNumberDTO;
import com.martirosovsk.bankservice.DTO.CardDTO;

import java.util.Set;

public interface CardService {
    /**
     * search all cards mapped to account
     *
     * @param id account ID
     * @return full set of cards
     */
    Set<CardDTO> findAllByAccountId(int id);

    /**
     * issue new card for account
     *
     * @param accountNumberDTO account ID
     * @return CardDTO of new card
     */
    CardDTO cardIssueByAccountNumber(AccountNumberDTO accountNumberDTO);
}
