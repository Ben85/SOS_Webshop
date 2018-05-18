package com.codecool.shop.controller;

import com.codecool.shop.config.Initializer;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.codecool.paypal.*;

@WebServlet(urlPatterns = {"paypal/execute-payment"})
public class ExecutePaymentController extends AbstractController {
    private static final String PAYER_DETAILS_REGEX = "paymentId=(.+)&payerId=(.+)";

    private PayerData getDataFromRequestBody(String requestBodyString) {
        Matcher matcher = Pattern.compile(PAYER_DETAILS_REGEX).matcher(requestBodyString);
        matcher.find();

        return new PayerData(matcher.group(1), matcher.group(2));
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean isPaymentComplete = Boolean.TRUE;

        try {
            File keyFile = new File(Initializer.servletContext.getRealPath("/") + "/data/paypal-login.key");

            AuthenticationCredentials authenticationCredentials = new AuthenticationCredentials(keyFile);
            Authenticator authenticator = new Authenticator(authenticationCredentials);

            PayerData payerData = getDataFromRequestBody(getContentAsString(req));
            PaymentExecutor executor = new PaymentExecutor(payerData);

            executor.executePayment(authenticator);
            getShoppingCart(req).getItemList().clear();
        }
        catch (IOException ignore) {
            isPaymentComplete = Boolean.FALSE;
        }

        HashMap<Object, Object> jsonData = new HashMap();
        jsonData.put("success", isPaymentComplete);

        renderJSON(new JSONObject(jsonData), resp);
    }
}
