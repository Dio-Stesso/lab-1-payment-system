package com.example.paymentsystem.controller;

import com.example.paymentsystem.dto.AccountDTO;
import com.example.paymentsystem.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/accounts")
public class AccountServlet extends HttpServlet {
    private AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long creditCardId = Long.parseLong(req.getParameter("creditCardId"));
        try {
            List<AccountDTO> accounts = accountService.findAllAccountsByCreditCardId(creditCardId);
            req.setAttribute("accounts", accounts);
            req.getRequestDispatcher("/account.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Double balance = Double.parseDouble(req.getParameter("balance"));
        String status = req.getParameter("status");
        Long creditCardId = Long.parseLong(req.getParameter("creditCardId"));
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setBalance(balance);
        accountDTO.setStatus(status);
        accountDTO.setCreditCardId(creditCardId);

        try {
            accountService.createAccount(accountDTO);
            resp.sendRedirect(req.getContextPath() + "/accounts?creditCardId=" + creditCardId);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
