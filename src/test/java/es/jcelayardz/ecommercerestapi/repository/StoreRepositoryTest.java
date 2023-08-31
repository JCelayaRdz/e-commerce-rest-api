package es.jcelayardz.ecommercerestapi.repository;

import es.jcelayardz.ecommercerestapi.model.Admin;
import es.jcelayardz.ecommercerestapi.model.AdminType;
import es.jcelayardz.ecommercerestapi.model.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class StoreRepositoryTest {

    @Container
    @ServiceConnection
    private static MySQLContainer<?> container = new MySQLContainer<>("mysql:latest")
            .withDatabaseName("e-commerce-rest-api")
            .withUsername("test")
            .withPassword("test");

    @Autowired
    private StoreRepository storeRepository;

    @BeforeEach
    void setUp() {
        Admin admin = new Admin("admin", "admin@gmail.com", "password", AdminType.STORE);

        Store store = new Store("Store 1", "description");
        store.setAdmin(admin);

        storeRepository.save(store);
    }

    @Test
    void testFindByName() {
        Optional<Store> result = storeRepository.findByName("Store 1");

        assertTrue(result.isPresent());

        Store store = result.get();

        assertEquals("Store 1", store.getName());
        assertEquals("description", store.getDescription());
        assertNull(store.getProducts());
        assertNotNull(store.getAdmin());
    }
}