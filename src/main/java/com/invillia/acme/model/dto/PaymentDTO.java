/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invillia.acme.model.dto;

import com.invillia.acme.model.enums.Status;
import java.time.LocalDate;

/**
 *
 * @author vinif
 */
public class PaymentDTO {

    private Long id;
    private Status status;
    private String creditCardNumber;
    private LocalDate paymentDate;

    public PaymentDTO() {
    }

    public PaymentDTO(Status status, String creditCardNumber, LocalDate paymentDate) {
        this.status = status;
        this.creditCardNumber = creditCardNumber;
        this.paymentDate = paymentDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "PaymentDTO{"
                + "id=" + id
                + ", status=" + status
                + ", creditCardNumber=" + creditCardNumber
                + ", paymentDate=" + paymentDate
                + '}';
    }
}
