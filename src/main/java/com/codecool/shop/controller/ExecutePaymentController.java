package com.codecool.shop.controller;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.paypal.PaypalAuthenticator;
import com.codecool.shop.paypal.PaypalTypeStructures;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import com.codecool.paypal.*;

@WebServlet(urlPatterns = {"paypal/execute-payment"})
public class ExecutePaymentController extends AbstractController {

    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            JSONObject requestJSON = (JSONObject) new JSONParser().parse(getContentAsString(req));
            File keyFile = new File(Initializer.servletContext.getRealPath("/") + "/data/paypal-login.key");

            AuthenticationCredentials authenticationCredentials = new AuthenticationCredentials(keyFile);
            Authenticator authenticator = new Authenticator(authenticationCredentials);

            PaymentExecutor executor = new PaymentExecutor(
                (String) requestJSON.get("payerId"),
                (String) requestJSON.get("paymentId")
            );
            executor.executePayment(authenticator);
            getShoppingCart(req).getItemList().clear();
        }
        catch (ParseException | IOException ignore) {
            resp.sendRedirect("/message?message-id=0");
        }
    }
}
