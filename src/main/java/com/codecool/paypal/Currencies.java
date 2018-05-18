package com.codecool.paypal;

public enum Currencies {
    AUSTRALIAN_DOLLAR ("AUD"),
    BRAZILIAN_REAL ("BRL"),
    CANADIAN_DOLLAR ("CAD"),
    CZECH_KORUNA ("CZK"),
    DANISH_KRONE ("DKK"),
    EURO ("EUR"),
    HONG_KONG_DOLLAR ("HKD"),
    HUNGARIAN_FORINT ("HUF"),
    INDIAN_RUPEE ("INR"),
    ISRAELI_NEW_SHEQEL ("ILS"),
    JAPANESE_YEN ("JPY"),
    MALAYSIAN_RINGGIT ("MYR"),
    MEXICAN_PESO ("MXN"),
    NORWEGIAN_KRONE ("NOK"),
    NEW_ZEALAND_DOLLAR ("NZD"),
    PHILIPPINE_PESO ("PHP"),
    POLISH_ZLOTY ("PLN"),
    POUND_STERLING ("GBP"),
    RUSSIAN_RUBLE ("RUB"),
    SINGAPORE_DOLLAR ("SGD"),
    SWEDISH_KRONA ("SEK"),
    SWISS_FRANC ("CHF"),
    TAIWAN_NEW_DOLLAR ("TWD"),
    THAI_BAHT ("THB"),
    US_DOLLAR ("USD");

    private String currencyString;

    public String getCurrencyString() {
        return currencyString;
    }

    @Override
    public String toString() {
        return getCurrencyString();
    }

    Currencies(String currencyString) {
        this.currencyString = currencyString;
    }
}
