package com.martirosovsk.bankservice.controller;


import com.martirosovsk.bankservice.BankServiceApplication;
import com.martirosovsk.bankservice.DTO.AccountDTO;
import com.martirosovsk.bankservice.DTO.BalanceChangeDTO;
import com.martirosovsk.bankservice.DTO.CardDTO;
import com.martirosovsk.bankservice.domain.Enums.Currency;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {BankServiceApplication.class})
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class AccountControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAccountDTOTest() {
        AccountDTO testDTO = new AccountDTO(1, 11, 0, Currency.RUB);
        ResponseEntity<AccountDTO> requestDTO =
                restTemplate.getForEntity("/api/accounts/1/", AccountDTO.class);
        assertTrue(requestDTO.getStatusCode().is2xxSuccessful());
        assertNotNull(requestDTO.getBody());
        assertEquals(requestDTO.getBody(), testDTO);
        assertEquals(requestDTO.getBody().getNumber(), 11);
    }


    @Test
    public void updateBalance() {
        BalanceChangeDTO balanceChangeDTO = new BalanceChangeDTO(21, 1000);
        AccountDTO testAccountDTO = new AccountDTO(3, 21, 1100, Currency.RUB);
        ResponseEntity<AccountDTO> requestDTO =
                restTemplate.postForEntity("/api/balance", balanceChangeDTO, AccountDTO.class);
        assertTrue(requestDTO.getStatusCode().is2xxSuccessful());
        assertNotNull(requestDTO.getBody());
        assertEquals(requestDTO.getBody(), testAccountDTO);

    }


}
