package es.jcelayardz.ecommercerestapi.repository;

import es.jcelayardz.ecommercerestapi.model.Admin;
import es.jcelayardz.ecommercerestapi.model.AdminType;
import es.jcelayardz.ecommercerestapi.model.Product;
import es.jcelayardz.ecommercerestapi.model.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class ProductRepositoryTest {

    @Container
    @ServiceConnection
    private static MySQLContainer<?> container = new MySQLContainer<>("mysql:latest")
            .withDatabaseName("e-commerce-rest-api")
            .withUsername("test")
            .withPassword("test");

    @Autowired
    private ProductRepository productRepository;

    private List<Product> products;

    @BeforeEach
    void setUp() {
        Admin admin = new Admin("admin", "admin@gmail.com", "password", AdminType.STORE);

        Store store = new Store("Store 1", "description");
        store.setAdmin(admin);

        Product notVisible = new Product("Apple Iphone 11", 334f, "64GB Black");
        notVisible.setVisible(false);
        notVisible.setStore(store);

        Product product1 = new Product("Apple Iphone 13 Pro Max", 912f, "128GB Blue");
        product1.setStore(store);

        Product product2 = new Product("Lenovo ThinkPad T470", 199f, "1920 × 1080 Full HD Intel Core i5 7200U 256 GB SSD");
        product2.setStore(store);

        products = List.of(notVisible,product1, product2);

        productRepository.saveAll(products);
    }

    @Test
    @DisplayName("Test find all visible products")
    void testFindVisibleProducts() {
        List<Product> visibleProducts = productRepository.findAllVisible();

        assertNotNull(visibleProducts);
        assertEquals(2, visibleProducts.size());
    }


}