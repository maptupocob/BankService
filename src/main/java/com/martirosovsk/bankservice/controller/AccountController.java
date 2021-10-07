package com.martirosovsk.bankservice.controller;

import com.martirosovsk.bankservice.DTO.AccountDTO;
import com.martirosovsk.bankservice.DTO.BalanceChangeDTO;
import com.martirosovsk.bankservice.service.AccountService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accSrv;

    @GetMapping("/balance")
    private long getBalanceByAccountId(@RequestParam("accountId") int accountId) {
        return accSrv.getBalanceByAccountId(accountId);
    }

    @PostMapping("/balance")
    private AccountDTO updateBalance(@RequestBody BalanceChangeDTO balanceChangeDTO) {
        return accSrv.updateBalance(balanceChangeDTO);
    }

    @GetMapping("/accounts/{accountId}")
    private AccountDTO getAccountInfo(@PathVariable("accountId") int accountId) {
        return accSrv.findById(accountId);
    }
}
