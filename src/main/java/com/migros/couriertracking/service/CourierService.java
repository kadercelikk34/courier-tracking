package com.migros.couriertracking.service;

import com.migros.couriertracking.dto.CourierDistance;
import com.migros.couriertracking.dto.CourierDto;
import com.migros.couriertracking.dto.LocationDto;

import java.util.List;


public interface CourierService {
    void saveCourier(CourierDto courierDto);

    void saveLocation(LocationDto locationDto);

    Double getTotalTravelDistance(Long courierId);

    List<CourierDistance> courierDistanceStore(LocationDto locationDto, Double speed);

}
