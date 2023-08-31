package es.jcelayardz.ecommercerestapi.service;

import es.jcelayardz.ecommercerestapi.exception.StoreNotFoundException;
import es.jcelayardz.ecommercerestapi.repository.AdminRepository;
import es.jcelayardz.ecommercerestapi.repository.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
}