package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Customer;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends AbstractController {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        initializeShoppingCart(req);

        ProductDao productDataStore = ProductDaoDatabase.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoDatabase.getInstance();

        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("recipient", "World");
        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("products", productDataStore);

        renderTemplate("product/index.html", req, resp, context);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        CustomerDaoDatabase customers = CustomerDaoDatabase.getInstance();
        List<Customer> customerList = customers.getAll();
        for (Customer customer : customerList) {
            if(username.equals(customer.getUsername())) {
                if(password.equals(customer.getHashedPassword())) {//Hash.isPasswordCorrect(password, customer.getHashedPassword())) {
                    session.setAttribute("customer", customer);
                    resp.sendRedirect("/");
                }
            } else {

            }
        }

    }
}
