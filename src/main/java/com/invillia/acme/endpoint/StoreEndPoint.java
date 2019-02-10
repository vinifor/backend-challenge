/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invillia.acme.endpoint;

import com.invillia.acme.model.Store;
import com.invillia.acme.model.dto.StoreDTO;
import com.invillia.acme.repository.StoreRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author vinicius
 */
@RestController
@RequestMapping("stores")
public class StoreEndPoint {

    @Autowired
    private StoreRepository storeRepository;

    private final  ModelMapper modelMapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<?> save(@RequestBody StoreDTO storeDTO) {
        return new ResponseEntity<>(storeRepository.save(modelMapper.map(storeDTO, Store.class)),
                HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody StoreDTO storeDTO) {
        if (storeRepository.findById(storeDTO.getId()).isPresent()) {
            return new ResponseEntity<>(storeRepository.save(modelMapper.map(storeDTO, Store.class)),
                    HttpStatus.OK);
        } else {
            return notFound();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Optional<Store> store = storeRepository.findById(id);
        if (store.isPresent()) {
            return new ResponseEntity<>(modelMapper.map(store, StoreDTO.class), HttpStatus.OK);
        } else {
            return notFound();
        }
    }

    @GetMapping(path = "/name/{name}")
    public ResponseEntity<?> findById(@PathVariable("name") String name) {
        List<Store> stores = storeRepository.findByNameIgnoreCaseContaining(name);
        if (!stores.isEmpty()) {
            return new ResponseEntity<>(stores.stream()
                    .map(store -> modelMapper.map(store, StoreDTO.class))
                    .collect(Collectors.toList()),
                    HttpStatus.OK);
        } else {
            return notFound();
        }
    }

    private ResponseEntity<?> notFound() {
        return new ResponseEntity<>("Couldn't find a store with given parameters",
                HttpStatus.NOT_FOUND);
    }
}
