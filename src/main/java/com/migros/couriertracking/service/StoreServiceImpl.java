package com.migros.couriertracking.service;

import com.migros.couriertracking.dto.StoreDto;
import com.migros.couriertracking.model.Store;
import com.migros.couriertracking.repository.StoreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = Exception.class)
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveStore(StoreDto storeDto) {
        Store store = modelMapper.map(storeDto, Store.class);
        storeRepository.save(store);

    }
}
