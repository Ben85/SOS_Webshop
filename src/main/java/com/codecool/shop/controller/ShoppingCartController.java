package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/shopping-cart"})
public class ShoppingCartController extends AbstractController {

    private final int DELETE_AMOUNT = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        initializeShoppingCart(req);

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("recipient", "World");
        context.setVariable("category", productCategoryDataStore.find(1));
        context.setVariable("shoppingCart", getShoppingCart(req).getItemList());
        context.setVariable("sumPrice", getShoppingCart(req).getSumPrice());

        renderTemplate("shopping-cart/shopping_cart.html", req, resp, context);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("productId"));
        getShoppingCart(req).changeItemQuantity(productId);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestBody = req.getReader().readLine();
        String[] requestBodyParts = requestBody.split("&");
        int productId = Integer.parseInt(requestBodyParts[0].replaceAll("\\D+", ""));
        int quantity = Integer.parseInt(requestBodyParts[1].replaceAll("\\D+", ""));
        getShoppingCart(req).changeItemQuantity(productId, quantity);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getReader().readLine().replaceAll("\\D+", ""));
        getShoppingCart(req).changeItemQuantity(productId, DELETE_AMOUNT);
    }

}
