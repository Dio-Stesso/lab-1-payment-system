package com.example.paymentsystem.service;

import com.example.paymentsystem.dto.PaymentDTO;
import com.example.paymentsystem.mapper.PaymentMapper;
import com.example.paymentsystem.model.Payment;
import com.example.paymentsystem.repository.PaymentRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentService {
    private PaymentRepository paymentRepository = new PaymentRepository();
    private PaymentMapper paymentMapper = PaymentMapper.INSTANCE;

    public void createPayment(PaymentDTO paymentDTO) throws SQLException {
        Payment payment = paymentMapper.toEntity(paymentDTO);
        paymentRepository.createPayment(payment);
    }

    public PaymentDTO findPaymentById(Long id) throws SQLException {
        Payment payment = paymentRepository.findPaymentById(id);
        return paymentMapper.toDTO(payment);
    }

    public List<PaymentDTO> findAllPaymentsByAccountId(Long accountId) throws SQLException {
        List<Payment> payments = paymentRepository.findAllPaymentsByAccountId(accountId);
        return payments.stream().map(paymentMapper::toDTO).collect(Collectors.toList());
    }

    public void updatePayment(PaymentDTO paymentDTO) throws SQLException {
        Payment payment = paymentMapper.toEntity(paymentDTO);
        paymentRepository.updatePayment(payment);
    }

    public void deletePayment(Long id) throws SQLException {
        paymentRepository.deletePayment(id);
    }
}
