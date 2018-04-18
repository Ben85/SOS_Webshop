package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.ShoppingCart;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/shopping-cart"})
public class ShoppingCartController extends AbstractController {

    private ShoppingCart shoppingCart;
    static final String SHOPPING_CART_SESSION_KEY = "shoppingCart";
    private final int DELETE_AMOUNT = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        initializeShoppingCart(req);

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

//        Map params = new HashMap<>();
//        params.put("category", productCategoryDataStore.find(1));
//        params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
//        context.setVariables(params);
        context.setVariable("recipient", "World");
        context.setVariable("category", productCategoryDataStore.find(1));
        context.setVariable("shoppingCart", getShoppingCart(req).getItemList());
        engine.process("shopping-cart/shopping_cart.html", context, resp.getWriter());
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

    ShoppingCart getShoppingCart(HttpServletRequest request) {
        shoppingCart = (ShoppingCart) request.getSession().getAttribute(SHOPPING_CART_SESSION_KEY);
        return shoppingCart;
    }

}
