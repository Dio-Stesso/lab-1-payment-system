package com.example.paymentsystem.controller;

import com.example.paymentsystem.dto.ClientDTO;
import com.example.paymentsystem.service.ClientService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/clients")
public class ClientServlet extends HttpServlet {
    private ClientService clientService = new ClientService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<ClientDTO> clients = clientService.findAllClients();
            req.setAttribute("clients", clients);
            req.getRequestDispatcher("/client.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName(name);
        clientDTO.setEmail(email);

        try {
            clientService.createClient(clientDTO);
            resp.sendRedirect(req.getContextPath() + "/clients");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
