package com.codecool.shop.controller;

import com.codecool.shop.model.Customer;
import com.codecool.shop.model.ShoppingCart;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class AbstractController extends HttpServlet {

    private final String SHOPPING_CART_SESSION_KEY = "shoppingCart";
    private final String CUSTOMER = "customer";
    private ShoppingCart shoppingCart;
    private Customer customer = null;

    void initializeShoppingCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.isNew()) {
            ShoppingCart shoppingCart = new ShoppingCart();
            session.setAttribute(SHOPPING_CART_SESSION_KEY, shoppingCart);
            session.setAttribute(CUSTOMER, null);
        }
    }

    ShoppingCart getShoppingCart(HttpServletRequest request) {
        shoppingCart = (ShoppingCart) request.getSession().getAttribute(SHOPPING_CART_SESSION_KEY);
        return shoppingCart;
    }

    Customer getCustomer(HttpServletRequest request) {
        customer = (Customer) request.getSession().getAttribute(CUSTOMER);
        return customer;
    }

}
