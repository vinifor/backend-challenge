/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invillia.acme.repository;

import com.invillia.acme.model.Payment;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author vinif
 */
public interface PaymentRepository extends CrudRepository<Payment, Long> {

}
