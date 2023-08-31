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

    @Test
    @DisplayName("Test save a valid store")
    void testSaveValidStore() {
        StoreDto storeToSave = new StoreDto (
                "Fashion Store",
                "A store that sells clothing items",
                "admin1"
        );

        Admin admin = new Admin("admin1", "admin1@gmail.com", "password", AdminType.STORE);

        Store store = storeToSave.toEntity();
        store.setAdmin(admin);

        when(adminRepository.findStoreAdminByUsername("admin1"))
                .thenReturn(Optional.of(admin));

        when(storeRepository.save(store))
                .thenReturn(store);

        StoreDto result = storeService.saveStore(storeToSave);

        assertEquals(storeToSave, result);
    }

    @Test
    @DisplayName("Test update an invalid store")
    void testUpdateInvalidStore() {
        StoreDto storeUpdated = new StoreDto (
                "Fashion Store",
                "A store that sells clothing items",
                "admin1"
        );

        doThrow(new StoreNotFoundException("Store 1"))
                .when(storeRepository)
                .findByName("Store 1");

        var exception = assertThrows(StoreNotFoundException.class, () -> {
           storeService.updateStore("Store 1", storeUpdated);
        });

        assertEquals("Could not find store with name Store 1", exception.getMessage());
    }

    @Test
    @DisplayName("Test update a valid store")
    void testUpdateValidStore() {
        StoreDto storeUpdated = new StoreDto (
                "Fashion Store",
                "A store that sells clothing items",
                "admin1"
        );
        storeUpdated.setVisible(false);

        Admin admin = new Admin("admin1", "admin1@gmail.com", "password", AdminType.STORE);

        Store originalStore = new Store("Store 1", null);
        originalStore.setAdmin(admin);

        Store store = storeUpdated.toEntity();
        store.setVisible(false);
        store.setAdmin(admin);

        when(storeRepository.findByName("Store 1"))
                .thenReturn(Optional.of(originalStore));

        when(storeRepository.save(store))
                .thenReturn(store);

        StoreDto result = storeService.updateStore("Store 1", storeUpdated);

        assertEquals(storeUpdated, result);
    }

    @Test
    @DisplayName("Test delete a store that does not exist")
    void testDeletNonExistentStore() {
        doThrow(new StoreNotFoundException("Non Existent"))
                .when(storeRepository)
                .findByName("Non Existent");

        var exception = assertThrows(StoreNotFoundException.class, () -> {
           storeService.deleteStore("Non Existent");
        });

        assertEquals("Could not find store with name Non Existent", exception.getMessage());
    }

    @Test
    @DisplayName("Test delete a store that does exist")
    void testDeleteExistingStore() {
        Store store = new Store("Existing", "A store that exists");

        when(storeRepository.findByName("Existing"))
                .thenReturn(Optional.of(store));

        assertDoesNotThrow(() -> storeService.deleteStore("Existing"));
    }
}