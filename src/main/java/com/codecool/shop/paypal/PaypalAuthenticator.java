package com.codecool.shop.paypal;

import com.codecool.shop.helper.HTTPRequestHelper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PaypalAuthenticator {
    public static class AuthenticationCredentials {
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

    private static final String PAYPAL_API_OAUTH2_URL = "https://api.sandbox.paypal.com/v1/oauth2/token";
    private AuthenticationCredentials authenticationCredentials;

    public String generateAccessToken() throws IOException {
        String authorizationValue = "Basic " + authenticationCredentials.getEncodedCredentials();

        String response = HTTPRequestHelper.getInstance().executePost(
            PAYPAL_API_OAUTH2_URL,
            "grant_type=client_credentials",
            new HashMap<String, String>() {{
                put("Accept","application/json");
                put("Accept-Language", "en_US");
                put("Authorization", authorizationValue);
            }}
        );

        try {
            JSONObject jsonResponse = (JSONObject) new JSONParser().parse(response);
            return (String) jsonResponse.get("access_token");
        }
        catch (ParseException ignore) {
            throw new IOException("Failed to parse PayPal authentication json response.");
        }
    }

    public PaypalAuthenticator(AuthenticationCredentials authenticationCredentials) {
        this.authenticationCredentials = authenticationCredentials;
    }
}
