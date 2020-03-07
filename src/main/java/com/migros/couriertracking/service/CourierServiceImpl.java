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

    @Autowired
    private CourierRepository courierRepository;

    @Autowired
    private LocationRepository locationRepository;


    @Autowired
    private ModelMapper modelMapper;

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
            List<Location> courierLocationsList = locationRepository.findByCourierId(courierId);
            for (int i = 0; i < courierLocationsList.size() - 1; i++) {
                total += DistanceCalculator.distanceAsMetric(courierLocationsList.get(i).getLatitude(), courierLocationsList.get(i).getLongitude(), courierLocationsList.get(i + 1).getLatitude(), courierLocationsList.get(i + 1).getLongitude());
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);

        }

        return total;
    }

    //Lokasyonu verlen kuryeye en fazla 100 metre uzak olan magazalrın listesini verir.
    @Override
    public List<CourierDistance> courierDistanceStore(LocationDto locationDto) {
        List<CourierDistance> courierDistanceList = new ArrayList<>();

        try {
            List<Location> storeLocationsList = locationRepository.findAllByStoreIsNotNull();

            storeLocationsList.stream().forEach(store -> {
                CourierDistance courierDistance = new CourierDistance();
                double total = DistanceCalculator.distanceAsMetric(locationDto.getLatitude(), locationDto.getLongitude(), store.getLatitude(), store.getLongitude());
                if (total < STORE_RADIUS) {
                    courierDistance.setDistance(DistanceCalculator.distanceFormat(total));
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
