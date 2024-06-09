package com.example.paymentsystem.controller;

import com.example.paymentsystem.dto.PaymentDTO;
import com.example.paymentsystem.service.PaymentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/payments")
public class PaymentServlet extends HttpServlet {
    private PaymentService paymentService = new PaymentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long accountId = Long.parseLong(req.getParameter("accountId"));
        try {
            List<PaymentDTO> payments = paymentService.findAllPaymentsByAccountId(accountId);
            req.setAttribute("payments", payments);
            req.getRequestDispatcher("/payment.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Double amount = Double.parseDouble(req.getParameter("amount"));
        String date = req.getParameter("date");
        String recipient = req.getParameter("recipient");
        Long accountId = Long.parseLong(req.getParameter("accountId"));
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmount(amount);
        paymentDTO.setDate(date);
        paymentDTO.setRecipient(recipient);
        paymentDTO.setAccountId(accountId);

        try {
            paymentService.createPayment(paymentDTO);
            resp.sendRedirect(req.getContextPath() + "/payments?accountId=" + accountId);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
