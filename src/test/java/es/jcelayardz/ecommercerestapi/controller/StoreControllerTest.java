package es.jcelayardz.ecommercerestapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.jcelayardz.ecommercerestapi.dto.StoreDto;
import es.jcelayardz.ecommercerestapi.service.StoreService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StoreController.class)
class StoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoreService storeService;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String BASE_URL = "/api/v1/stores";

    @Test
    @DisplayName("Test get store by name")
    void testGetStoreByName() throws Exception {
        StoreDto store = new StoreDto("Store 1", "This is a store", "admin2");

        when(storeService.getStoreByName("Store 1"))
                .thenReturn(store);

        mockMvc.perform(get(BASE_URL + "/Store 1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Store 1")))
                .andExpect(jsonPath("$.description", is("This is a store")))
                .andExpect(jsonPath("$.adminUsername", is("admin2")));
    }

    @Test
    @DisplayName("Test save an invalid store")
    void testSaveInvalidStore() throws Exception {
        StoreDto storeToSave = new StoreDto(
                null,
                "A store that sells technological items",
                null
        );

        String errorMessage = "Validation errors: The name and adminUsername fields must not be empty";

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(storeToSave)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.type", is(isA(String.class))))
                .andExpect(jsonPath("$.title", is("BAD REQUEST")))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.detail", is(errorMessage)))
                .andExpect(jsonPath("$.instance", is("about:blank")));
    }

    @Test
    @DisplayName("Test save a valid product")
    void testSaveValidProduct() throws Exception {
        StoreDto storeToSave = new StoreDto(
                "Tech Store",
                "A store that sells technological items",
                "admin2"
        );

        when(storeService.saveStore(storeToSave))
                .thenReturn(storeToSave);

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(storeToSave)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Tech Store")))
                .andExpect(jsonPath("$.description", is("A store that sells technological items")))
                .andExpect(jsonPath("$.adminUsername", is("admin2")));
    }

    @Test
    @DisplayName("Test update an invalid product")
    void testUpdateInvalidProduct() throws Exception{
        StoreDto storeToUpdated = new StoreDto(
                "Tech Store",
                "A store that sells technological items",
                null
        );

        String errorType = "https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/400";
        String errorMessage = "Validation errors: The name and adminUsername fields must not be empty";

        mockMvc.perform(put(BASE_URL + "/Store 1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(storeToUpdated)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.type", is(errorType)))
                .andExpect(jsonPath("$.title", is("BAD REQUEST")))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.detail", is(errorMessage)))
                .andExpect(jsonPath("$.instance", is(Is.isA(String.class))));
    }

    @Test
    @DisplayName("Test update a valid product")
    void testUpdateValidProduct() throws Exception {
        StoreDto storeToUpdated = new StoreDto(
                "Tech Store",
                "A store that sells technological items",
                "admin2"
        );

        when(storeService.updateStore("Store 1", storeToUpdated))
                .thenReturn(storeToUpdated);

        mockMvc.perform(put(BASE_URL + "/Store 1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(storeToUpdated)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Tech Store")))
                .andExpect(jsonPath("$.description", is("A store that sells technological items")))
                .andExpect(jsonPath("$.adminUsername", is("admin2")));
    }
}