package com.martirosovsk.bankservice.domain.Enums;

public enum Currency {
    RUB(100),
    USD(100),
    EUR(100);
    private final int precision;
    Currency(int precision){
        this.precision = precision;
    }
    public int getPrecision(){ return precision;}
}
