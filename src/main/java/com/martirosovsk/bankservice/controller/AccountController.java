package com.martirosovsk.bankservice.controller;

import com.martirosovsk.bankservice.DTO.AccountDTO;
import com.martirosovsk.bankservice.service.AccountService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/api/accounts/")
public class AccountController {

    private final AccountService accSrv;

    @GetMapping("/{accountId}/balance")
    private long getBalanceByAccountId(@PathVariable("accountId") int accountId){
        return accSrv.getBalanceByAccountId( accountId);
    }

    @PostMapping("/{accountId}/balance")
    private AccountDTO updateBalance(@PathVariable("accountId") int accountId,
                                     @RequestParam("amount") int amount) {
        return accSrv.updateBalance(accountId, amount);
    }

    @GetMapping("/{accountId}")
    private AccountDTO getAccountInfo(@PathVariable("accountId") int accountId) {
        return accSrv.findById(accountId);
    }


}
