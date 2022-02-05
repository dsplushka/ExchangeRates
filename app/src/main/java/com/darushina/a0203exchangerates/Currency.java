package com.darushina.a0203exchangerates;

public class Currency {
    public String charCode;
    public String value;

    public Currency(String charCode, String value) {
        this.charCode = charCode;
        this.value = value;
    }

    public String getCharCode() {
        return charCode;
    }

    public String getValue() {
        return value;
    }
}