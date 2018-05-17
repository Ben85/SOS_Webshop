package com.codecool.shop.controller;

import com.codecool.shop.model.Customer;
import com.codecool.shop.model.ShoppingCart;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"message"})
public class MessageController extends AbstractController {

    private static final String[] MESSAGES = new String[] {
        "Hiba történt a tranzakció lebonyolítása során.",
        "A tranzakció megszakítva.",
        "A tranzakció sikeresen megtörtént.",
        "Adjon valamit a kosárhoz, mielőtt folytatná a vásárlási folyamatot."
    };

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        initializeShoppingCart(req);

        WebContext context = new WebContext(req, resp, req.getServletContext());
        ShoppingCart shoppingCart = getShoppingCart(req);
        Customer customer = getCustomer(req);

        String message = MESSAGES[0];

        try {
            message = MESSAGES[Integer.valueOf(req.getParameter("message-id"))];
        } catch (NumberFormatException | NullPointerException ignore) {}

        context.setVariable("message", message);

        renderTemplate("message.html", req, resp, context);
    }
}
