package com.codecool.paypal.basetypes;

import com.codecool.paypal.CountryCodes;
import org.json.simple.JSONAware;

public abstract class BaseAddress extends BaseStructure {
    protected String addressLine1;
    protected String addressLine2;
    protected String countryCode;
    protected String postalCode;
    protected String state;
    protected String city;

    protected BaseAddress(
        String addressLine1,
        String addressLine2,
        CountryCodes countryCode,
        String postalCode,
        String state,
        String city
    ) {
        if (countryCode.isPostalCodeRequired() && postalCode == null) {
            throw new NullPointerException("The country code requires a postal code");
        }

        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.countryCode = countryCode.toString();
        this.postalCode = postalCode;
        this.state = state;
        this.city = city;
    }
}
