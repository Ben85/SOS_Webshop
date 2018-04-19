package com.codecool.shop.controller;


import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Customer;
import com.codecool.shop.model.ShoppingCart;
import org.thymeleaf.TemplateEngine;
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
        initializeShoppingCart(req);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        ShoppingCart shoppingCart = getShoppingCart(req);
        Customer customer = getCustomer(req);

        //String name, String email, String phoneNum, String bZip, String zip,
        // String city, String bCity, String address, String bAddress
        Customer test = new Customer("Lapos Elemér", "em@ail.com", "14548989", "8888", "8888",
                "Város", "Város", "cím", "cím");

        context.setVariable("shoppingCart", shoppingCart.getItemList());
        context.setVariable("customer", customer);
        engine.process("checkout/summary.html", context, resp.getWriter());


    }
}
