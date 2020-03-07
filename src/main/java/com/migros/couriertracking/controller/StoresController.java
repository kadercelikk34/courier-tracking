package com.migros.couriertracking.controller;

import com.migros.couriertracking.dto.StoreDto;
import com.migros.couriertracking.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/stores")
public class StoresController {
    private static final Logger logger = LoggerFactory.getLogger(StoresController.class);

    @Autowired
    private StoreService storeService;

    //Yenibir magaza oluşturur.
    @PostMapping
    public ResponseEntity<URI> saveStore(@RequestBody StoreDto storeDto) {
        try {
            storeService.saveStore(storeDto);
            Long id = storeDto.getId();
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
