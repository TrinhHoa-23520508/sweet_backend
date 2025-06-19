package com.example.sweet.util.constant;

public enum SystemParameterEnum {
    // Cai nay no se luon luon la 1 cai enum, khong bao gio thay doi
    MIN_AGE_EMPLOYEE(1),
    MIN_AGE_CUSTOMER(2),
    MIN_DEPOSIT_AMOUNT(3),
    NO_TERM_INTEREST_RATE(4);

    private final int code;

    private SystemParameterEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
