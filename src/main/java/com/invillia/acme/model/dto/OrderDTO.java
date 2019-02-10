/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invillia.acme.model.dto;

import com.invillia.acme.model.enums.Status;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author vinif
 */
public class OrderDTO {

    private Long id;
    private String address;
    private LocalDate confirmationDate;
    private Status status;
    private PaymentDTO payment;
    private List<OrderItemDTO> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "OrderDTO{"
                + "id=" + id
                + ", address=" + address
                + ", confirmationDate=" + confirmationDate
                + ", status=" + status
                + ", payment=" + payment
                + ", items=" + items
                + '}';
    }

}
