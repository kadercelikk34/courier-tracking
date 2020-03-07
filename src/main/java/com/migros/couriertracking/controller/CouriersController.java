package com.migros.couriertracking.controller;

import com.migros.couriertracking.dto.CourierDistance;
import com.migros.couriertracking.dto.CourierDto;
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
import java.util.List;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;


@RestController
@RequestMapping("/couriers")
public class CouriersController {
    private static final Logger logger = LoggerFactory.getLogger(CouriersController.class);


    @Autowired
    private CourierService courierService;

    //Yeni Kurye oluşturulur.
    @PostMapping
    public ResponseEntity<URI> saveCourier(@RequestBody CourierDto courierDto) {
        try {
            courierService.saveCourier(courierDto);
            Long id = courierDto.getId();
            URI url = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
            logger.info("this is a info message");
            return ResponseEntity.created(url).build();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //id si verilen kuryenin aldığı toplam mesafeyi verir
    @GetMapping(value = "/{id}")
    public @ResponseBody
    Double getTotalTravelDistance(@PathVariable("id") Long id) {
        Double totalTravelDistance = courierService.getTotalTravelDistance(id);
        return totalTravelDistance;
    }

    //Lokasyonu verilen kuryenin 100 metre yakınındaki magazaların listesini(Magaza ismi  ve mesafesi)
    @GetMapping(value = "/courierDistanceStores")
    public ResponseEntity<List<CourierDistance>> courierDistanceStores(@RequestBody LocationDto locationDto) {
        try {
            List<CourierDistance> list = courierService.courierDistanceStore(locationDto);
            return ResponseEntity.ok(list);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
