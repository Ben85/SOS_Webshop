package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.CustomerDaoDatabase;
import com.codecool.shop.helper.Hash;
import com.codecool.shop.model.Customer;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends AbstractController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String plainPassword = req.getParameter("password");
        Customer customer = new Customer(
                req.getParameter("firstName"),
                req.getParameter("lastName"),
                Hash.hashPassword(plainPassword),
                req.getParameter("email"),
                Integer.parseInt(req.getParameter("phoneNum")),
                req.getParameter("zipCode"),
                req.getParameter("city"),
                req.getParameter("address"),
                req.getParameter("billingZipCode"),
                req.getParameter("billingCity"),
                req.getParameter("billingAddress"),
                req.getParameter("username"),
                req.getParameter("sameAsAbove")
        );

        HttpSession session = req.getSession();
        session.setAttribute("customer", customer);
        CustomerDaoDatabase.getInstance().add(customer);

        resp.sendRedirect("/summary");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        if (getShoppingCart(req).getItemList().isEmpty()) {
            resp.sendRedirect("/message?message-id=3");
        }

        renderTemplate("checkout/checkout.html", req, resp, context);
    }
}
