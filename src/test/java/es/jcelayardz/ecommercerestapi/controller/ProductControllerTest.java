package es.jcelayardz.ecommercerestapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.jcelayardz.ecommercerestapi.dto.ProductDto;
import es.jcelayardz.ecommercerestapi.service.ProductService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private List<ProductDto> products;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String baseUrl = "/api/v1/products";

    @BeforeEach
    void beforeEach() {
        products = new ArrayList<>();

        products.add(new ProductDto(
                1,
                "Iphone 14 Pro",
                1319.00f,
                "Iphone 14 Pro 128GB Deep Purple",
                "Store 1"));
        products.add(new ProductDto(
                2,
                "Xiaomi 13 Ultra",
                1499.99f,
                "Xiaomi 13 Ultra 12 GB + 512 GB Green",
                "Store 2"
        ));
        products.add(new ProductDto(
                3,
                "Intel Core i5-12400F",
                162.99f,
                "Intel Core i5-12400F 2.5Ghz",
                "Store 3"
        ));
    }

    @AfterEach
    void afterEach() {
        products.clear();
    }

    @Test
    @DisplayName("Test get all products")
    void testGetAllProducts() throws Exception {

        when(productService.getAllProducts())
                .thenReturn(products);

        mockMvc.perform(get(baseUrl))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andExpect(jsonPath("$[0].productId", is(1)))
                .andExpect(jsonPath("$[1].name", is("Xiaomi 13 Ultra")))
                .andExpect(jsonPath("$[2].price", is(162.99)));
    }

    @Test
    @DisplayName("Test get product by id")
    void testGeProductById() throws Exception {
        when(productService.getProductById(1))
                .thenReturn(products.get(0));

        mockMvc.perform(get(baseUrl + "/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Iphone 14 Pro")))
                .andExpect(jsonPath("$.price", is(1319.0)))
                .andExpect(jsonPath("$.description", Is.isA(String.class)))
                .andExpect(jsonPath("$.storeName", is("Store 1")));
    }

    @Test
    @DisplayName("Test save a valid product")
    void testSaveValidProduct() throws Exception {
        ProductDto product = new ProductDto(
                100,
                "MacBook Pro Touch Bar 15\"",
                682.04f,
                null,
                "Store 100"
        );

        when(productService.saveProduct(product))
                .thenReturn(product);

        mockMvc.perform(post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(product)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Test save invalid product")
    void testSaveInvalidProduct() throws Exception {
        ProductDto product = new ProductDto(
                100,
                null,
                682.04f,
                null,
                "Store 100"
        );

        mockMvc.perform(post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(product)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Test update valid product")
    void testUpdateValidProduct() throws Exception {
        ProductDto productToUpdate = products.get(1);
        productToUpdate.setDescription("updated");

        when(productService.updateProduct(2, productToUpdate))
                .thenReturn(productToUpdate);

        mockMvc.perform(put(baseUrl + "/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(productToUpdate)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Xiaomi 13 Ultra")))
                .andExpect(jsonPath("$.description", is("updated")));
    }

    @Test
    @DisplayName("Test delete product")
    void testDeleteProduct() throws Exception {
        mockMvc.perform(delete(baseUrl + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

}