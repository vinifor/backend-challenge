/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invillia.acme.endpoint;

import com.invillia.acme.model.Store;
import com.invillia.acme.repository.StoreRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author vinicius
 */
@RestController
@RequestMapping("stores")
public class StoreEndPoint {

    private final StoreRepository storeRepository;

    public StoreEndPoint(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Store store) {
        return new ResponseEntity<>(storeRepository.save(store), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Store store) {
        if (storeRepository.findById(store.getId()).isPresent()) {
            return new ResponseEntity<>(storeRepository.save(store), HttpStatus.OK);
        } else {
            return notFound();
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(storeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Optional<Store> store = storeRepository.findById(id);
        if (store.isPresent()) {
            return new ResponseEntity<>(store, HttpStatus.OK);
        } else {
            return notFound();
        }
    }

    @GetMapping(path = "/byName/{name}")
    public ResponseEntity<?> findById(@PathVariable("name") String name) {
        List<Store> stores = storeRepository.findByNameIgnoreCaseContaining(name);
        if (!stores.isEmpty()) {
            return new ResponseEntity<>(stores, HttpStatus.OK);
        } else {
            return notFound();
        }
    }

    private ResponseEntity<?> notFound() {
        return new ResponseEntity<>("Couldn't find a store with given parameters",
                HttpStatus.NOT_FOUND);
    }
}
