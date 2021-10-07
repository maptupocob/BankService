package com.martirosovsk.bankservice.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CardNumberUtilTest {

    @Test
    public void getNextCardNumber() {
        assertEquals(CardNumberUtil.getNextCardNumber().length(), 19);
    }
}