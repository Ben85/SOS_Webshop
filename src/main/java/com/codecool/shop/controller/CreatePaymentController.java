package com.codecool.shop.controller;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;
import com.codecool.paypal.*;
import org.json.simple.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import com.codecool.paypal.*;

@WebServlet(urlPatterns = {"paypal/create-payment"})
public class CreatePaymentController extends AbstractController {
    private static final String PAYPAL_RETURN_URL = "http://localhost:8080/message?message-id=2";
    private static final String PAYPAL_CANCEL_URL = "http://localhost:8080/message?message-id=0";

    private ItemList convertShoppingCartToItemList(ShoppingCart shoppingCart) {
        return new ItemList(new LinkedList<Item>() {{
            for (Map.Entry<Product, Integer> cartEntry: shoppingCart.getItemList().entrySet()) {
                Product product = cartEntry.getKey();
                Integer quantity = cartEntry.getValue();

                add(new Item(
                    quantity,
                    product.getName(),
                    product.getDefaultPrice().floatValue(),
                    product.getDefaultCurrency().toString(),
                    product.getDescription(),
                    0.0f
                ));
            }
        }});
    }

    private String generateInvoiceNumber() {
        int firstPart = ThreadLocalRandom.current().nextInt(100, 999 + 1);
        int secondPart = ThreadLocalRandom.current().nextInt(1000000, 9999999 + 1);

        return String.format("%d-%d", firstPart, secondPart);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File keyFile = new File(Initializer.servletContext.getRealPath("/") + "/data/paypal-login.key");

        AuthenticationCredentials authenticationCredentials = new AuthenticationCredentials(keyFile);
        Authenticator authenticator = new Authenticator(authenticationCredentials);

        ItemList items = convertShoppingCartToItemList(this.getShoppingCart(req));

        TransactionList transactions = new TransactionList(
            new LinkedList<Transaction>() {{
                add(new Transaction(
                    items,
                    new TransactionAmount(
                        items.getTotalItemValue(),
                        "HUF",
                        items.getTotalTaxValue(),
                        0.0f,
                        0.0f
                    ),
                    "Ferivel kapcsolatos ideiglenes placeholder leiras helye...",
                    generateInvoiceNumber(),
                    "Codecool 2017"
                ));
            }}
        );

        Payment payment = new Payment(
            "sale",
            null,
            new RedirectURLs(
                PAYPAL_RETURN_URL,
                PAYPAL_CANCEL_URL
            ),
            new Payer(
                "paypal"
            ),
            transactions
        );

        try {
            String id = payment.processPayment(authenticator);

            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("id", id);
            resp.setContentType("application/json");

            renderJSON(jsonResponse, resp);
        } catch (IOException ignore) {}
    }
}