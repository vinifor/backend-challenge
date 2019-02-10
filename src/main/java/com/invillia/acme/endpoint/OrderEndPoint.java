/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invillia.acme.endpoint;

import com.invillia.acme.model.Order;
import com.invillia.acme.model.Payment;
import com.invillia.acme.model.dto.OrderDTO;
import com.invillia.acme.model.dto.PaymentDTO;
import com.invillia.acme.repository.OrderRepository;
import com.invillia.acme.repository.PaymentRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author vinicius
 */
@RestController
@RequestMapping("orders")
public class OrderEndPoint {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<?> save(@RequestBody OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
        order.getOrderItems().forEach(item -> item.setOrder(order));

        return createResponse(orderRepository.save(order), HttpStatus.CREATED);
    }

    @PostMapping(path = "/{id}/payment")
    public ResponseEntity<?> addPayment(@PathVariable("id") Long id, @RequestBody PaymentDTO paymentDTO) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            if (order.get().getPayment() == null) {
                return createResponse(orderRepository
                        .save(order.get()
                                .addPayment(paymentRepository
                                        .save(modelMapper.map(paymentDTO, Payment.class)))),
                        HttpStatus.CREATED);
            } else {
                return conflict("Payment already exists for Order: " + id);
            }
        } else {
            return notFound();
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return notFound();
        }
    }

    @GetMapping(path = "/date/{date}")
    public ResponseEntity<?> findByDate(@PathVariable("date") LocalDate date) {
        List<Order> orders = orderRepository.findByConfirmationDateGreaterThan(date);
        if (!orders.isEmpty()) {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } else {
            return notFound();
        }
    }

    private ResponseEntity<?> createResponse(Order order, HttpStatus status) {
        return new ResponseEntity<>(
                modelMapper.map(order, OrderDTO.class),
                status);
    }

    private ResponseEntity<?> notFound() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<?> conflict(String message) {
        return new ResponseEntity<>(message,
                HttpStatus.CONFLICT);
    }
}
