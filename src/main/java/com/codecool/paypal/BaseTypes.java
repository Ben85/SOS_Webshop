package com.codecool.paypal;

import org.json.simple.JSONAware;

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

        public void setProperty(String fieldName, Object newValue) throws NoSuchFieldException {
            try {
                Field field = this.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(this, newValue.toString());
            }
            catch (IllegalAccessException ignore) {}
        }

        public abstract JSONAware toJSON();
    }
}
