package com.codecool.shop.controller;

import com.codecool.shop.model.ShoppingCart;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class AbstractController extends HttpServlet {

    void initializeShoppingCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.isNew()) {
            ShoppingCart shoppingCart = new ShoppingCart();
            session.setAttribute(ShoppingCartController.SHOPPING_CART_SESSION_KEY, shoppingCart);
        }
    }

}
