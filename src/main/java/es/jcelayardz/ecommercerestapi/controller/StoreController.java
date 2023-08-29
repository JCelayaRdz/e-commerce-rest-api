package es.jcelayardz.ecommercerestapi.controller;

import es.jcelayardz.ecommercerestapi.dto.StoreDto;
import es.jcelayardz.ecommercerestapi.service.StoreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stores")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/{storeName}")
    public StoreDto getStoreByName(@PathVariable String storeName) {
        return storeService.getStoreByName(storeName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StoreDto saveStore(@Valid @RequestBody StoreDto store) {
        return storeService.saveStore(store);
    }
}
