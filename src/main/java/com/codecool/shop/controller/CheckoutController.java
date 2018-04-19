package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Customer;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends AbstractController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Customer customer = new Customer(
                req.getParameter("user_name"),
                req.getParameter("email"),
                req.getParameter("phoneNum"),
                req.getParameter("bZip"),
                req.getParameter("zip"),
                req.getParameter("city"),
                req.getParameter("bCity"),
                req.getParameter("address"),
                req.getParameter("bAddress"));

        resp.sendRedirect("/summary");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        engine.process("checkout/checkout.html", context, resp.getWriter());

    }
}
