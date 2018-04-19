package com.codecool.shop.controller;

import com.codecool.shop.model.ShoppingCart;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class AbstractController extends HttpServlet {

    private final String SHOPPING_CART_SESSION_KEY = "shoppingCart";
    private ShoppingCart shoppingCart;

    void initializeShoppingCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.isNew()) {
            ShoppingCart shoppingCart = new ShoppingCart();
            session.setAttribute(SHOPPING_CART_SESSION_KEY, shoppingCart);
        }
    }

    ShoppingCart getShoppingCart(HttpServletRequest request) {
        shoppingCart = (ShoppingCart) request.getSession().getAttribute(SHOPPING_CART_SESSION_KEY);
        return shoppingCart;
    }

}
