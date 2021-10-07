package com.martirosovsk.bankservice.controller;

import com.martirosovsk.bankservice.BankServiceApplication;
import com.martirosovsk.bankservice.DTO.AccountNumberDTO;
import com.martirosovsk.bankservice.DTO.CardDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {BankServiceApplication.class})
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class CardControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void findAllByAccountIdTest() {
        CardDTO[] cardDTOArr = new CardDTO[]{new CardDTO(1, "1234 5678 9012 1234"),
                new CardDTO(2, "1234 5678 9012 1235"),
                new CardDTO(3, "1234 5678 9012 1236")};
        ResponseEntity<CardDTO[]> requestDTOArr =
                restTemplate.getForEntity("/api/cards/all?accountId=1", CardDTO[].class);
        assertTrue(requestDTOArr.getStatusCode().is2xxSuccessful());
        assertNotNull(requestDTOArr.getBody());
        System.out.println(Arrays.toString(requestDTOArr.getBody()));
        assertArrayEquals(cardDTOArr, requestDTOArr.getBody());
    }

    @Test
    public void cardIssueByAccountIDTest() {
        AccountNumberDTO accountNumberDTO = new AccountNumberDTO(22);
        ResponseEntity<CardDTO> requestDTO =
                restTemplate.postForEntity("/api/cards/issue", accountNumberDTO, CardDTO.class);
        assertTrue(requestDTO.getStatusCode().is2xxSuccessful());
        assertNotNull(requestDTO.getBody());
        assertEquals(requestDTO.getBody().getNumber().length(), 19);
    }
}