package es.jcelayardz.ecommercerestapi.service;

import es.jcelayardz.ecommercerestapi.dto.ProductDto;
import es.jcelayardz.ecommercerestapi.dto.StoreDto;
import es.jcelayardz.ecommercerestapi.exception.StoreBadRequestException;
import es.jcelayardz.ecommercerestapi.exception.StoreNotFoundException;
import es.jcelayardz.ecommercerestapi.model.Admin;
import es.jcelayardz.ecommercerestapi.model.AdminType;
import es.jcelayardz.ecommercerestapi.model.Product;
import es.jcelayardz.ecommercerestapi.model.Store;
import es.jcelayardz.ecommercerestapi.repository.AdminRepository;
import es.jcelayardz.ecommercerestapi.repository.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StoreServiceTest {

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private StoreService storeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test retrieving a non-existent store")
    void testRetrieveNonExistentStore() {

        when(storeRepository.findByName("Non Existent"))
                .thenReturn(Optional.empty());

        var exception = assertThrows(StoreNotFoundException.class, () -> {
            storeService.getStoreByName("Non Existent");
        });

        assertEquals("Could not find store with name Non Existent", exception.getMessage());
    }

    @Test
    @DisplayName("Test retrieving an existent store")
    void testRetrieveExistingStore() {
        Store store = new Store("Tech Store", "A store that sells technological items");
        store.setAdmin(new Admin("admin2", "admin2@gmail.com", "pass", AdminType.STORE));

        Product product1 = new Product(
                "HP Chromebook 14a-na1011ns",
                259f,
                "Laptop 14 Full HD (Intel Celeron N4500, 4GB RAM, 64GB eMMC)"
        );
        product1.setStore(store);

        Product product2 = new Product(
                "MSI Optix G24C4",
                149f,
                "23.6\" Curved Gaming Monitor LED FullHD 144 Hz"
        );
        product2.setStore(store);

        store.setProducts(Set.of(product1, product2));

        when(storeRepository.findByName("Tech Store"))
                .thenReturn(Optional.of(store));

        StoreDto result = storeService.getStoreByName("Tech Store");

        assertEquals("Tech Store", result.getName());
        assertEquals("A store that sells technological items", result.getDescription());
        assertEquals("admin2", result.getAdminUsername());
        assertEquals(2, result.getProducts().size());
        assertEquals(
                new ProductDto(
                        "MSI Optix G24C4",
                        149f,
                        "23.6\" Curved Gaming Monitor LED FullHD 144 Hz",
                        "Tech Store"),
                result.getProducts().get(1)
        );

    }

    @Test
    @DisplayName("Test save an invalid store")
    void testSaveInvalidStore() {
        StoreDto storeToSave = new StoreDto (
                "Fashion Store",
                "A store that sells clothing items",
                "admin1"
        );

        doThrow(new StoreBadRequestException("admin1"))
                .when(adminRepository)
                .findStoreAdminByUsername("admin1");

        var exception = assertThrows(StoreBadRequestException.class, () -> {
           storeService.saveStore(storeToSave);
        });

        assertEquals("Could not find a store admin with username admin1", exception.getMessage());
    }
}