package com.migros.couriertracking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.migros.couriertracking.dto.CourierDto;
import com.migros.couriertracking.model.Courier;
import com.migros.couriertracking.model.Location;
import com.migros.couriertracking.service.CourierService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CouriersController.class)

public class CouriersControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CourierService courierService;

    @Test
    public void testSaveCourier() throws Exception {
        CourierDto courierDto = new CourierDto();
        courierDto.setFirstName("TestName");
        courierDto.setLastName("TestLastName");

        mvc.perform(post("/couriers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(courierDto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCourierDistanceStores() throws Exception {
        Location location = new Location();
        location.setLatitude(40.991254);
        location.setLongitude(40.991254);
        Courier courier = new Courier();
        courier.setId(1L);
        location.setCourier(courier);
        mvc.perform(get("/couriers/courierDistanceStores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(location)))
                .andExpect(status().isOk());

    }
}
