package com.martirosovsk.bankservice.DAO;

import com.martirosovsk.bankservice.domain.Card;

import java.util.Set;

public interface CardDAO {
    /**
     * search all cards owned by client
     *
     * @param id client ID
     * @return set of cards
     */
    Set<Card> findAllByAccountID(int id);

    /**
     * save new card
     *
     * @param card card entity without ID
     * @return generated ID
     */
    int saveCard(Card card);
}
