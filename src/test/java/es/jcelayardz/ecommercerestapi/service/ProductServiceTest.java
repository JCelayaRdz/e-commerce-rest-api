package es.jcelayardz.ecommercerestapi.service;

import es.jcelayardz.ecommercerestapi.dto.ProductDto;
import es.jcelayardz.ecommercerestapi.model.Product;
import es.jcelayardz.ecommercerestapi.model.Store;
import es.jcelayardz.ecommercerestapi.repository.ProductRepository;
import es.jcelayardz.ecommercerestapi.repository.StoreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private ProductService productService;

    private List<Product> products;

    private List<Store> stores;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);

        products = new ArrayList<>();

        Store store1 = new Store("Store 1", "store 1");

        Store store2 = new Store("Store 2", "store 2");

        Product notVisible = new Product(
                "Iphone 14 Pro",
                1319.00f,
                "Iphone 14 Pro 128GB Deep Purple");
        notVisible.setVisible(false);
        notVisible.setStore(store1);

        Product product1 = new Product(
                "Xiaomi 13 Ultra",
                1499.99f,
                "Xiaomi 13 Ultra 12 GB + 512 GB Green"
        );
        product1.setStore(store1);
        store1.setProducts(Set.of(notVisible, product1));

        Product product2 = new Product(
                "Intel Core i5-12400F",
                162.99f,
                "Intel Core i5-12400F 2.5Ghz"
        );
        product2.setStore(store2);
        store2.setProducts(Set.of(product2));

        products.add(product1);
        products.add(product2);

        stores = new ArrayList<>();

        stores.add(store1);
        stores.add(store2);
    }

    @AfterEach
    void afterEach() {
        products.clear();
    }

    @Test
    @DisplayName("Test get all visible products")
    void testGetAllVisibleProducts() {
        when(productRepository.findAllVisible())
                .thenReturn(products);

        List<ProductDto> result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(
                new ProductDto(
                        null,
                        "Intel Core i5-12400F",
                        162.99f,
                        "Intel Core i5-12400F 2.5Ghz",
                        "Store 2"),
                result.get(1)
        );
    }

    @Test
    @DisplayName("Test get product by id that exists")
    void testGetProductByIdNotVisible() {
        when(productRepository.findById(1))
                .thenReturn(Optional.ofNullable(products.get(0)));

        ProductDto result = productService.getProductById(1);

        assertNotNull(result);
        assertEquals(
                new ProductDto(
                null,
                "Xiaomi 13 Ultra",
                1499.99f,
                "Xiaomi 13 Ultra 12 GB + 512 GB Green",
                "Store 1"),
                result
        );

    }

}