/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invillia.acme.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.invillia.acme.model.enums.Status;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author vinif
 */
@Entity
@Table(name = "orders")
public class Order extends AbstractEntity {

    @Column(name = "address")
    private String address;

    @Column(name = "confirmation_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate confirmationDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    public Order() {
    }

    public Order(String address, LocalDate confirmationDate, Status status) {
        this.address = address;
        this.confirmationDate = confirmationDate;
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(LocalDate confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "Order{"
                + "address=" + address
                + ", confirmationDate=" + confirmationDate
                + ", status=" + status
                + '}';
    }

    public boolean canRefound() {
        return ChronoUnit.DAYS
                .between(confirmationDate, LocalDate.now()) <= 10;
    }

    public Order addPayment(Payment payment) {
        setPayment(payment);
        return this;
    }

    public static class Builder {

        private String address;
        private LocalDate confirmationDate;
        private Status status;

        public Builder() {
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setConfirmationDate(LocalDate confirmationDate) {
            this.confirmationDate = confirmationDate;
            return this;
        }

        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public Order build() {
            return new Order(address, confirmationDate, status);
        }

    }
}
