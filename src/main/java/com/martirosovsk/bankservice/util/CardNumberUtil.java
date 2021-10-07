package com.martirosovsk.bankservice.util;

public class CardNumberUtil {
    private static final int bankId = 1234_56;
    private static long cardId = 12_3456_7890;

    /**
     * generating new full card number
     *
     * @return card number string
     */
    public static String getNextCardNumber() {
        long cardId = getNextCardId() + (long) (1_0000 * Math.random());
        int firstBankGroup = bankId / 100;
        int secondBankGroup = bankId % 100;
        int firstCardGroup = (int) (cardId / 1_0000_0000);
        int secondCardGroup = (int) ((cardId / 1_0000) % 1_0000);
        int thirdCardGroup = (int) (cardId % 1_0000);
        return String.format("%d %d%d %04d %04d", firstBankGroup, secondBankGroup, firstCardGroup, secondCardGroup, thirdCardGroup);
    }

    /**
     * generating next unique card part of card number
     *
     * @return new card part of card number
     */
    private static long getNextCardId() {
        return cardId++;
    }
}
