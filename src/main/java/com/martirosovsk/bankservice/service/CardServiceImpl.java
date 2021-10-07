package com.martirosovsk.bankservice.service;

import com.martirosovsk.bankservice.DAO.AccountDAO;
import com.martirosovsk.bankservice.DAO.CardDAO;
import com.martirosovsk.bankservice.DCO.CardDCO;
import com.martirosovsk.bankservice.DTO.AccountNumberDTO;
import com.martirosovsk.bankservice.DTO.CardDTO;
import com.martirosovsk.bankservice.domain.Card;
import com.martirosovsk.bankservice.util.CardNumberUtil;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Data
public class CardServiceImpl implements CardService {

    private final CardDAO crdDAO;
    private final CardDCO crdDCO;
    private final AccountDAO accDAO;

    @Override
    public Set<CardDTO> findAllByAccountId(int clientId) {
        return crdDAO.findAllByAccountID(clientId).stream()
                .map(crdDCO::cardToDTO).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public CardDTO cardIssueByAccountNumber(AccountNumberDTO accountNumberDTO) {
        try {
            int accId = accDAO.findByNumber(accountNumberDTO.getNumber()).getId();
            Card card = new Card();
            card.setAccountID(accId);
            card.setNumber(CardNumberUtil.getNextCardNumber());
            int id = crdDAO.saveCard(card);
            card.setId(id);
            return crdDCO.cardToDTO(card);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
