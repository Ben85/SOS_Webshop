package com.codecool.shop.controller;

import com.codecool.shop.model.Customer;
import com.codecool.shop.model.ShoppingCart;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MessageController extends AbstractController {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        initializeShoppingCart(req);

        WebContext context = new WebContext(req, resp, req.getServletContext());
        ShoppingCart shoppingCart = getShoppingCart(req);
        Customer customer = getCustomer(req);

        context.setVariable("message", req.getParameter("message"));

        renderTemplate("message.html", req, resp, context);
    }
}
