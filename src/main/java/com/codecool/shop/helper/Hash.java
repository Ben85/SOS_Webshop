package com.codecool.shop.helper;

import org.mindrot.jbcrypt.BCrypt;

public class Hash {

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean isPasswordCorrect(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

}
