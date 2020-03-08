package com.migros.couriertracking.repository;


import com.migros.couriertracking.model.Courier;
import com.migros.couriertracking.model.Location;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LocationRepositoryIntegrationTest {
    @Autowired
    private LocationRepository locationRepository;

    @Test
    public void testSaveLocation() {
        Location location = new Location();
        location.setLatitude(40.9923307);
        location.setLongitude(29.1244229);
        Courier courier = new Courier();
        courier.setFirstName("TestName");
        courier.setLastName("TestLastName");
        location.setCourier(courier);
        Location saveLocation = locationRepository.save(location);
        MatcherAssert.assertThat(saveLocation.getLatitude(), Matchers.equalTo(location.getLatitude()));
        MatcherAssert.assertThat(saveLocation.getLongitude(), Matchers.equalTo(location.getLongitude()));
        MatcherAssert.assertThat(saveLocation.getCourier().getFirstName(), Matchers.equalTo(location.getCourier().getFirstName()));
        MatcherAssert.assertThat(saveLocation.getCourier().getLastName(), Matchers.equalTo(location.getCourier().getLastName()));


    }
}
