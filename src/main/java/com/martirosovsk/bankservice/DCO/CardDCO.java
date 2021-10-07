package com.martirosovsk.bankservice.DCO;

import com.martirosovsk.bankservice.DTO.CardDTO;
import com.martirosovsk.bankservice.domain.Card;
import org.springframework.stereotype.Component;

@Component
public class CardDCO {
    public CardDTO cardToDTO(Card c) {
        return new CardDTO(c.getId(), c.getNumber());
    }
}
