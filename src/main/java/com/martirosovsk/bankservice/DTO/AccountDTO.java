package com.martirosovsk.bankservice.DTO;

import com.martirosovsk.bankservice.domain.Enums.Currency;
import lombok.Data;

@Data
public class AccountDTO {
    private final int id;
    private final int number;
    private final long balance;
    private final Currency currency;
}
