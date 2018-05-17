package com.codecool.shop.controller;


import com.codecool.shop.model.Customer;
import com.codecool.shop.model.ShoppingCart;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/summary"})
public class SummaryController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        ShoppingCart shoppingCart = getShoppingCart(req);
        Customer customer = getCustomer(req);

        context.setVariable("shoppingCart", shoppingCart.getItemList());
        context.setVariable("customer", customer);

        renderTemplate("checkout/summary.html", req, resp, context);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paymentMethod = req.getParameter("payment");
        if (paymentMethod.equals("payAtDelivery")) {
            resp.sendRedirect("/pay-at-delivery");
        } else if (paymentMethod.equals("paypal")) {
            resp.sendRedirect("/");
        }
    }
}
