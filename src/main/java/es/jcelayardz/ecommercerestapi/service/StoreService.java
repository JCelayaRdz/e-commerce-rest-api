package es.jcelayardz.ecommercerestapi.service;

import es.jcelayardz.ecommercerestapi.dto.StoreDto;
import es.jcelayardz.ecommercerestapi.exception.StoreNotFoundException;
import es.jcelayardz.ecommercerestapi.model.Store;
import es.jcelayardz.ecommercerestapi.repository.StoreRepository;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public StoreDto getStoreByName(String storeName) {
        return storeRepository.findByName(storeName)
                .map(Store::toDto)
                .orElseThrow(() -> new StoreNotFoundException(storeName));
    }
}
