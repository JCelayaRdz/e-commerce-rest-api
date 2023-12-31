package es.jcelayardz.ecommercerestapi.service;

import es.jcelayardz.ecommercerestapi.dto.ProductDto;
import es.jcelayardz.ecommercerestapi.exception.ProductNotFoundException;
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
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private ProductService productService;

    private List<Product> visibleProducts;

    private List<Product> products;

    private List<Store> stores;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);

        products = new ArrayList<>();

        visibleProducts = new ArrayList<>();

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

        products.addAll(List.of(notVisible, product1, product2));

        visibleProducts.addAll(List.of(product1, product2));

        stores = new ArrayList<>();
        stores.addAll(List.of(store1, store2));
    }

    @AfterEach
    void afterEach() {
        products.clear();
        stores.clear();
    }

    @Test
    @DisplayName("Test get all visible products")
    void testGetAllVisibleProducts() {
        when(productRepository.findAllVisible())
                .thenReturn(visibleProducts);

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
                .thenReturn(Optional.ofNullable(visibleProducts.get(0)));

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

    @Test
    @DisplayName("Test get product by id that does not exist")
    void testGetProductByIdNotExists() {
        when(productRepository.findById(100))
                .thenReturn(Optional.empty());

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
            productService.getProductById(100);
        });

        assertEquals("Could not found product with id 100",exception.getMessage());
    }

    @Test
    @DisplayName("Test save a product")
    void testSaveProduct() {
        ProductDto productToSave = new ProductDto(
                10,
                "JBL TUNE 510BT",
                49.99f,
                "JBL Wireless Headphones",
                "Store 2"
        );

        Product product = productToSave.toEntity();
        product.setProductId(10);
        product.setStore(stores.get(1));

        when(storeRepository.findByName("Store 2"))
                .thenReturn(Optional.ofNullable(stores.get(1)));

        when(productRepository.save(product))
                .thenReturn(product);

        // TODO: Check why it returns a null object
        ProductDto result = productService.saveProduct(productToSave);

        assertEquals(productToSave, result);

    }

    @Test
    @DisplayName("Test update product that does not exist")
    void testUpdateProductNotExists() {
        when(productRepository.findById(100))
                .thenReturn(Optional.empty());

        ProductDto productToUpdate = new ProductDto(
                100,
                "Product",
                100f,
                "description",
                "Store 1"
        );

        ProductNotFoundException ex = assertThrows(ProductNotFoundException.class, () -> {
           productService.updateProduct(100, productToUpdate);
        });

        assertEquals("Could not found product with id 100",ex.getMessage());
    }

    @Test
    @DisplayName("Test update product that exists")
    void testUpdateProductExists() {
        Product productToUpdate = visibleProducts.get(1);

        ProductDto productUpdated = new ProductDto(
                "Intel Core i5-12400F",
                162.99f,
                "updated",
                "Store 2"
        );

        when(productRepository.findById(2))
                .thenReturn(Optional.ofNullable(productToUpdate));

        when(productRepository.save(productToUpdate))
                .thenReturn(productToUpdate);

        ProductDto result = productService.updateProduct(2, productUpdated);

        assertEquals(productUpdated, result);

    }

    @Test
    @DisplayName("Test delete product that does not exist")
    void testDeleteProductNotExists() {
        when(productRepository.findById(50))
                .thenReturn(Optional.empty());

        ProductNotFoundException ex = assertThrows(ProductNotFoundException.class, () -> {
            productService.deleteProduct(50);
        });

        assertEquals("Could not found product with id 50",ex.getMessage());
    }

}