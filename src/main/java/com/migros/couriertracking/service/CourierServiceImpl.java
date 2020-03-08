package com.migros.couriertracking.service;

import com.migros.couriertracking.dto.CourierDistance;
import com.migros.couriertracking.dto.CourierDto;
import com.migros.couriertracking.dto.LocationDto;
import com.migros.couriertracking.model.Courier;
import com.migros.couriertracking.model.Location;
import com.migros.couriertracking.repository.CourierRepository;
import com.migros.couriertracking.repository.LocationRepository;
import com.migros.couriertracking.utils.DistanceCalculator;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CourierServiceImpl implements CourierService {
    private static final Logger logger = LoggerFactory.getLogger(CourierServiceImpl.class);

    private static final int STORE_RADIUS = 100;
    private static final int DISTANCE_MINUTE = 1;


    private CourierRepository courierRepository;

    private LocationRepository locationRepository;

    private ModelMapper modelMapper;

    @Autowired
    public CourierServiceImpl(CourierRepository courierRepository, LocationRepository locationRepository, ModelMapper modelMapper) {
        this.courierRepository = courierRepository;
        this.locationRepository = locationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveCourier(CourierDto courierDto) {
        Courier courier = modelMapper.map(courierDto, Courier.class);
        courierRepository.save(courier);
    }

    @Override
    public void saveLocation(LocationDto locationDto) {
        Location location = modelMapper.map(locationDto, Location.class);
        locationRepository.save(location);
    }

    //id si verilen kuryenin ziyaret ettiği  mesafe toplamını  verir.
    @Override
    public Double getTotalTravelDistance(Long courierId) {
        double total = 0.0;
        try {
            List<Location> list = locationRepository.findByCourierId(courierId);
            for (int i = 0; i < list.size() - 1; i++) {
                total += DistanceCalculator.distanceAsMetric(list.get(i).getLatitude(), list.get(i).getLongitude(), list.get(i + 1).getLatitude(), list.get(i + 1).getLongitude());
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);

        }

        return total;
    }

    //Lokasyonu verilen kuryeye en fazla 100 metre uzak olan magazaları listesini verir.1 dk mesafedeki magazalar giriş yapılmaz.
    @Override
    public List<CourierDistance> courierDistanceStore(LocationDto locationDto, Double speed) {
        List<CourierDistance> courierDistanceList = new ArrayList<>();
        try {
            List<Location> storeLocationsList = locationRepository.findAllByStoreIsNotNull();
            CourierDistance courierDistance = new CourierDistance();
            storeLocationsList.forEach(store -> {
                double distanceTotal = DistanceCalculator.distanceAsMetric(locationDto.getLatitude(), locationDto.getLongitude(), store.getLatitude(), store.getLongitude());
                double distanceTime = DistanceCalculator.distanceAsTime(distanceTotal, speed);
                if (distanceTotal < STORE_RADIUS && distanceTime > DISTANCE_MINUTE) {
                    courierDistance.setCourierDistance(DistanceCalculator.distanceFormat(distanceTotal));
                    courierDistance.setDistanceMinute(distanceTime);
                    courierDistance.setStoreName(store.getStore().getStoreName());
                    courierDistanceList.add(courierDistance);
                }
            });
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);

        }
        return courierDistanceList;
    }
}
