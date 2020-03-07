package com.migros.couriertracking.repository;

import com.migros.couriertracking.model.Courier;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CourierRepositoryIntegrationTest {
    @Autowired
    private CourierRepository courierRepository;


    @Test
    public void testSaveCourier() {
        Courier courier = new Courier();
        courier.setFirstName("TestName");
        courier.setLastName("TestLastName");
        Courier saveCourier = courierRepository.save(courier);
        MatcherAssert.assertThat(saveCourier.getFirstName(), Matchers.equalTo(courier.getFirstName()));
        MatcherAssert.assertThat(saveCourier.getLastName(), Matchers.equalTo(courier.getLastName()));

    }

}
