package com.migros.couriertracking.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.migros.couriertracking.dto.StoreDto;
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
@WebMvcTest(controllers = StoresController.class)
public class StoresControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StoreService storeService;


    @Test
    public void testSaveStore() throws Exception {
        StoreDto storeDto = new StoreDto();
        storeDto.setStoreName("Test MMM Migros");
        storeDto.setStoreAddress("Migros Test Adres");
       //given(storeService.saveStore(storeDto)).willReturn(void);

        mvc.perform(post("/stores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(storeDto)))
                .andExpect(status().isCreated());

    }
}
