/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invillia.acme.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author vinif
 */
@Entity
public class OrderItem extends AbstractEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "quantity")
    private Long quantity;

    @JoinColumn(name = "orders", referencedColumnName = "id")
    @ManyToOne
    private Order order;

    public OrderItem() {
    }

    public OrderItem(String description, BigDecimal unitPrice, Long quantity, Order order) {
        this.description = description;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.order = order;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderItem{"
                + "description=" + description
                + ", unitPrice=" + unitPrice
                + ", quantity=" + quantity
                + ", order=" + order
                + '}';
    }

    public static class Builder {

        private String description;
        private BigDecimal unitPrice;
        private Long quantity;
        private Order order;

        public Builder() {
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setUnitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public Builder setQuantity(Long quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setOrder(Order order) {
            this.order = order;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(description, unitPrice, quantity, order);
        }

    }

}
