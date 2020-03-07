package com.migros.couriertracking.controller;

import com.migros.couriertracking.dto.LocationDto;
import com.migros.couriertracking.service.CourierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/locations")
public class LocationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationController.class);

    private CourierService courierService;

    @Autowired
    public LocationController(CourierService courierService) {
        this.courierService = courierService;
    }

    //Kurye ve Magaza lokasyonları kaydedilir.
    @PostMapping
    public ResponseEntity<URI> saveLocation(@RequestBody LocationDto locationDto) {
        try {
            courierService.saveLocation(locationDto);
            Long id = locationDto.getId();
            URI url = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
            return ResponseEntity.created(url).build();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
