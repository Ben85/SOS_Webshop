package com.codecool.paypal;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class AuthenticationCredentials {
    private String encodedCredentials;

    public String getEncodedCredentials() {
        return encodedCredentials;
    }

    private void setEncodedCredentials(String clientId, String secretKey) {
        String credentials = String.format(
            "%s:%s",
            clientId,
            secretKey
        );

        encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
    }

    public AuthenticationCredentials(String clientId, String secretKey) {
        setEncodedCredentials(clientId, secretKey);
    }

    public AuthenticationCredentials(File keyFile) {
        try (Scanner scanner = new Scanner(keyFile)) {
            setEncodedCredentials(scanner.nextLine(), scanner.nextLine());
        }
        catch (IOException | NoSuchElementException exception) {
            exception.printStackTrace();
        }
    }
}