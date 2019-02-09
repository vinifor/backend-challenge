/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invillia.acme.model;

import com.invillia.acme.model.enums.Status;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author vinif
 */
@Entity
public class Payment extends AbstractEntity {

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "credit_card_number")
    private String creditCardNumber;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    public Payment() {
    }

    public Payment(Status status, String creditCardNumber, LocalDate paymentDate) {
        this.status = status;
        this.creditCardNumber = creditCardNumber;
        this.paymentDate = paymentDate;
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
        return "Payment{"
                + "status=" + status
                + ", creditCardNumber=" + creditCardNumber
                + ", paymentDate=" + paymentDate
                + '}';
    }

    public static class Builder {

        private Status status;
        private String creditCardNumber;
        private LocalDate paymentDate;

        public Builder() {
        }

        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder setCreditCardNumber(String creditCardNumber) {
            this.creditCardNumber = creditCardNumber;
            return this;
        }

        public Builder setPaymentDate(LocalDate paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public Payment build() {
            return new Payment(status, creditCardNumber, paymentDate);
        }

    }
}
