package com.migros.couriertracking.service;

import com.migros.couriertracking.dto.CourierDistance;
import com.migros.couriertracking.dto.LocationDto;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

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
        List<CourierDistance> courierDistances = courierService.courierDistanceStore(locationDto);
        List<String> storeNameList = courierDistances.stream().map(a -> a.getStoreName()).collect(Collectors.toList());
        MatcherAssert.assertThat(storeNameList, Matchers.containsInAnyOrder("Ataşehir MMM Migros"));

    }

}
