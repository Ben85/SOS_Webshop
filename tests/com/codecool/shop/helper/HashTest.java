package com.codecool.shop.helper;

import org.junit.jupiter.api.Test;

import static junit.framework.Assert.*;

public class HashTest {

    @Test
    public void checkCorrectPassword() {
        String hash = Hash.hashPassword("testpassword");
        assertTrue(Hash.isPasswordCorrect("testpassword", hash));
    }

    @Test
    public void checkIncorrectPassword() {
        String hash = Hash.hashPassword("testpassword");
        assertFalse(Hash.isPasswordCorrect("something", hash));
    }

}
