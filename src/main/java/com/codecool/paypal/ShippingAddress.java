package com.codecool.paypal;

import com.codecool.paypal.basetypes.BaseAddress;
import com.codecool.paypal.helper.JSONVerifier;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class ShippingAddress extends BaseAddress {

    private String recipientName;
    private String phone;

    @Override
    public JSONAware toJSON() {
        return JSONVerifier.verifyObject(new JSONObject() {{
            put("recipient_name", recipientName);
            put("phone", phone);
            put("line1", addressLine1);
            put("line2", addressLine2);
            put("country_code", countryCode);
            put("postal_code", postalCode);
            put("state", state);
            put("city", city);
        }});
    }

    public ShippingAddress(
        String recipientName,
        String phone,
        String addressLine1,
        String addressLine2,
        CountryCodes countryCode,
        String postalCode,
        String state,
        String city
    ) {
        super(addressLine1, addressLine2, countryCode, postalCode, state, city);

        this.recipientName = recipientName;
        this.phone = phone;
    }

}
