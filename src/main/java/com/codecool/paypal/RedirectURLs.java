package com.codecool.paypal;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class RedirectURLs extends BaseTypes.BaseStructureType {
    private String returnURL;
    private String cancelURL;

    @Override
    public JSONAware toJSON() {
        return new JSONObject() {{
            put("return_url", returnURL == null ? "" : returnURL);
            put("cancel_url", cancelURL == null ? "" : cancelURL);
        }};
    }

    public RedirectURLs(String returnURL, String cancelURL) {
        this.returnURL = returnURL;
        this.cancelURL = cancelURL;
    }
}
