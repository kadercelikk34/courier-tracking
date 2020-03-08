package com.migros.couriertracking.service;

import com.migros.couriertracking.dto.CourierDistance;
import com.migros.couriertracking.dto.LocationDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CourierServiceImplIntegrationTest {

    @Mock
    private CourierService courierService;

    @Test
    public void testGetTotalTravelDistance() {
        when(courierService.getTotalTravelDistance(1L)).thenReturn(889.6786922956344);

    }

    @Test
    public void testCourierDistanceStore() {
        LocationDto locationDto = new LocationDto();
        locationDto.setLatitude(40.9915988);
        locationDto.setLongitude(29.1238999);
        List<CourierDistance> list = new ArrayList<>();
        list.add(new CourierDistance("Ataşehir MMM Migros", "92.5 m"));
        when(courierService.courierDistanceStore(locationDto, 40.0)).thenReturn(list);

    }


}
