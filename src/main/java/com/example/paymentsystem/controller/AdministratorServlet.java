package com.example.paymentsystem.controller;

import com.example.paymentsystem.service.AdministratorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/unblock")
public class AdministratorServlet extends HttpServlet {
    private final AdministratorService administratorService = new AdministratorService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long accountId = Long.parseLong(req.getParameter("accountId"));

        try {
            administratorService.unblockAccount(accountId);
            resp.sendRedirect(req.getContextPath() + "/accounts?creditCardId=" + accountId);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
