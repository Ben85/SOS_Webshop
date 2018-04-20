package com.codecool.shop.controller;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.helper.HTTPRequestHelper;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.paypal.PaypalAuthenticator;
import com.codecool.shop.paypal.PaypalTypeStructures;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.security.sasl.AuthenticationException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet(urlPatterns = {"paypal/create-payment"})
public class CreatePaymentController extends AbstractController {
    private static final String PAYPAL_RETURN_URL = "https://example.com";
    private static final String PAYPAL_CANCEL_URL = "https://example.com";

    private PaypalTypeStructures.ItemList convertShoppingCartToItemList(ShoppingCart shoppingCart) {
        return new PaypalTypeStructures.ItemList(new LinkedList<PaypalTypeStructures.Item>() {{
            for (Map.Entry<Product, Integer> cartEntry: shoppingCart.getItemList().entrySet()) {
                Product product = cartEntry.getKey();
                Integer quantity = cartEntry.getValue();

                add(new PaypalTypeStructures.Item(
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File keyFile = new File(Initializer.servletContext.getRealPath("/") + "/data/paypal-login.key");

        PaypalAuthenticator.AuthenticationCredentials authenticationCredentials
            = new PaypalAuthenticator.AuthenticationCredentials(keyFile);

        PaypalAuthenticator authenticator = new PaypalAuthenticator(authenticationCredentials);
        PaypalTypeStructures.ItemList itemList = convertShoppingCartToItemList(this.getShoppingCart(req));

        PaypalTypeStructures.PaypalPayment payment = new PaypalTypeStructures.PaypalPayment(
            "sale",
            null,
            new PaypalTypeStructures.RedirectURLs(
                PAYPAL_RETURN_URL,
                PAYPAL_CANCEL_URL
            ),
            new PaypalTypeStructures.Payer(
                "paypal"
            ),
            new PaypalTypeStructures.Transactions(
                itemList,
                new PaypalTypeStructures.TransactionAmount(
                    itemList.getTotalItemValue(),
                    "HUF",
                    itemList.getTotalTaxValue(),
                    0.0f,
                    0.0f
                ),
                "Ferivel kapcsolatos ideiglenes placeholder leiras helye...",
                generateInvoiceNumber(),
                "Codecool 2017"
            )
        );

        String id = "ERROR";
        try {
            id = payment.processPayment(authenticator);
        } catch (IOException ignore) {}

        JSONObject jsonResponse = new JSONObject();
        resp.setContentType("application/json");
        jsonResponse.put("id", id);

        PrintWriter out = resp.getWriter();
        out.print(jsonResponse.toJSONString());
        out.flush();
    }
}