package es.jcelayardz.ecommercerestapi.service;

import es.jcelayardz.ecommercerestapi.dto.StoreDto;
import es.jcelayardz.ecommercerestapi.exception.StoreBadRequestException;
import es.jcelayardz.ecommercerestapi.exception.StoreNotFoundException;
import es.jcelayardz.ecommercerestapi.model.Admin;
import es.jcelayardz.ecommercerestapi.model.Store;
import es.jcelayardz.ecommercerestapi.repository.AdminRepository;
import es.jcelayardz.ecommercerestapi.repository.StoreRepository;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    private final AdminRepository adminRepository;

    public StoreService(StoreRepository storeRepository, AdminRepository adminRepository) {
        this.storeRepository = storeRepository;
        this.adminRepository = adminRepository;
    }

    public StoreDto getStoreByName(String storeName) {
        return storeRepository.findByName(storeName)
                .map(Store::toDto)
                .orElseThrow(() -> new StoreNotFoundException(storeName));
    }

    public StoreDto saveStore(StoreDto storeDto) {
        // To make sure that the admin exists and is indeed a store admin
        Admin storeAdmin = adminRepository.findStoreAdminByUsername(storeDto.getAdminUsername())
                .orElseThrow(() -> new StoreBadRequestException(storeDto.getAdminUsername()));

        Store store = storeDto.toEntity();
        store.setAdmin(storeAdmin);

        return storeRepository.save(store).toDto();
    }
}
