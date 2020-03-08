package com.migros.couriertracking.repository;


import com.migros.couriertracking.model.Store;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StoreRepositoryIntegrationTest {
    @Autowired
    private StoreRepository storeRepository;

    @Test
    public void testSaveStore() {
        Store store = new Store();
        store.setStoreName("Test MMM Migros");
        store.setStoreAddress("Migros Test Adres");
        Store saveStore = storeRepository.save(store);
        MatcherAssert.assertThat(saveStore.getStoreName(), Matchers.equalTo(store.getStoreName()));
        MatcherAssert.assertThat(saveStore.getStoreAddress(), Matchers.equalTo(store.getStoreAddress()));

    }
}
