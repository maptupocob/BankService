package com.martirosovsk.bankservice.controller;

import com.martirosovsk.bankservice.DTO.AccountNumberDTO;
import com.martirosovsk.bankservice.DTO.CardDTO;
import com.martirosovsk.bankservice.service.CardService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Data
@RestController
@RequestMapping("/api/cards")
public class CardController {

    private final CardService crdSrv;

    @GetMapping()
    public Set<CardDTO> findAllByAccountId(@RequestParam("accountId") int accId) {
        return crdSrv.findAllByAccountId(accId);
    }

    @PostMapping()
    private CardDTO cardIssueByAccountID(@RequestBody() AccountNumberDTO accountNumberDTO) {
        return crdSrv.cardIssueByAccountNumber(accountNumberDTO);
    }
}
