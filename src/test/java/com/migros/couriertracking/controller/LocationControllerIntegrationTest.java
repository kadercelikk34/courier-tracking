package com.migros.couriertracking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.migros.couriertracking.dto.CourierDto;
import com.migros.couriertracking.dto.LocationDto;
import com.migros.couriertracking.service.CourierService;
import com.migros.couriertracking.service.StoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = LocationController.class)
public class LocationControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CourierService courierService;

    @Test
    public void testSaveLocation() throws Exception {
        LocationDto locationDto = new LocationDto();
        locationDto.setLatitude(40.9923307);
        locationDto.setLongitude(29.1244229);
        CourierDto courierDto = new CourierDto();
        courierDto.setFirstName("TestName");
        courierDto.setLastName("TestLastName");
        locationDto.setCourier(courierDto);
        //given(storeService.saveStore(storeDto)).willReturn(void);

        mvc.perform(post("/locations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(locationDto)))
                .andExpect(status().isCreated());

    }

}
