package com.codecool.paypal;

import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import java.lang.reflect.Field;

class BaseTypes {
    public interface BaseJSONType {
        JSONAware toJSON();
    }

    public static abstract class BaseStructureType implements BaseJSONType {
        public String getProperty(String fieldName) throws NoSuchFieldException {
            try {
                Field field = this.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                return (String) field.get(this);
            }
            catch (IllegalAccessException ignore) {
                return null;
            }
        }

        public abstract JSONAware toJSON();
    }
}
