package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Customer;
import com.codecool.shop.model.ShoppingCart;
import org.json.simple.JSONObject;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public abstract class AbstractController extends HttpServlet {

    private final String SHOPPING_CART_SESSION_KEY = "shoppingCart";
    private final String CUSTOMER = "customer";
    private ShoppingCart shoppingCart;
    private Customer customer;

    void initializeShoppingCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.isNew()) {
            ShoppingCart shoppingCart = new ShoppingCart();
            session.setAttribute(SHOPPING_CART_SESSION_KEY, shoppingCart);
        }
    }

    ShoppingCart getShoppingCart(HttpServletRequest request) {
        shoppingCart = (ShoppingCart) request.getSession().getAttribute(SHOPPING_CART_SESSION_KEY);
        if (shoppingCart == null) {
            initializeShoppingCart(request);
            return getShoppingCart(request);
        }
        return shoppingCart;
    }

    Customer getCustomer(HttpServletRequest request) {
        customer = (Customer) request.getSession().getAttribute(CUSTOMER);
        return customer;
    }

    private void writeUTF8ToBuffer(String content, ServletOutputStream out)
        throws IOException
    {
        out.write(content.getBytes("UTF-8"));
        out.flush();
    }

    void renderJSON(JSONObject jsonResponse,HttpServletResponse response)
        throws IOException
    {
        writeUTF8ToBuffer(jsonResponse.toJSONString(), response.getOutputStream());
    }

    void renderTemplate(
        String templateName,
        HttpServletRequest request,
        HttpServletResponse response,
        WebContext context
    )
        throws IOException
    {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        String template = engine.process(templateName, context);
        writeUTF8ToBuffer(template, response.getOutputStream());
    }

}
