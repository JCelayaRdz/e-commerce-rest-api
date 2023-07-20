package es.jcelayardz.ecommercerestapi.controller;

import es.jcelayardz.ecommercerestapi.dto.ProductDto;
import es.jcelayardz.ecommercerestapi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ProductDto getProductById(@PathVariable Integer productId) {
        return productService.getProductById(productId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto saveProduct(@Valid @RequestBody ProductDto product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/{productId}")
    public ProductDto updateProduct(@PathVariable Integer productId,
                                    @Valid @RequestBody ProductDto product) {
        return productService.updateProduct(productId, product);
    }
}
