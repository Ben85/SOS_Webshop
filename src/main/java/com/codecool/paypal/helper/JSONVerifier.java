package com.codecool.paypal.helper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
class JSONVerifier {
    private static JSONArray verifyArray(JSONArray jsonArray) {
        return new JSONArray() {{
            for (Object value : jsonArray) {
                if (value != null) {
                    add(value);
                }
            }
        }};
    }

    private static JSONObject verifyObject(JSONObject jsonObject) {
        return new JSONObject() {{
            for (Object key : jsonObject.keySet()) {
                Object value = jsonObject.get(key);
                if (value != null) {
                    put(key, value);
                }
            }
        }};
    }
}
