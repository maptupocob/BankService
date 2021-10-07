package com.martirosovsk.bankservice.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalanceChangeDTO {
    private int accountNumber;
    private int balanceChange;
}
