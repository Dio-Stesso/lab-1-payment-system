package com.example.paymentsystem.controller;

import com.example.paymentsystem.dto.CreditCardDTO;
import com.example.paymentsystem.service.CreditCardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/creditcards")
public class CreditCardServlet extends HttpServlet {
    private CreditCardService creditCardService = new CreditCardService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long clientId = Long.parseLong(req.getParameter("clientId"));
        try {
            List<CreditCardDTO> creditCards = creditCardService.findAllCreditCardsByClientId(clientId);
            req.setAttribute("creditCards", creditCards);
            req.getRequestDispatcher("/creditcard.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cardNumber = req.getParameter("cardNumber");
        String expiryDate = req.getParameter("expiryDate");
        String cvv = req.getParameter("cvv");
        Long clientId = Long.parseLong(req.getParameter("clientId"));
        CreditCardDTO creditCardDTO = new CreditCardDTO();
        creditCardDTO.setCardNumber(cardNumber);
        creditCardDTO.setExpiryDate(expiryDate);
        creditCardDTO.setCvv(cvv);
        creditCardDTO.setClientId(clientId);

        try {
            creditCardService.createCreditCard(creditCardDTO);
            resp.sendRedirect(req.getContextPath() + "/creditcards?clientId=" + clientId);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
