package es.jcelayardz.ecommercerestapi.service;

import es.jcelayardz.ecommercerestapi.dto.ProductDto;
import es.jcelayardz.ecommercerestapi.exception.ProductBadRequestException;
import es.jcelayardz.ecommercerestapi.exception.ProductNotFoundException;
import es.jcelayardz.ecommercerestapi.model.Product;
import es.jcelayardz.ecommercerestapi.model.Store;
import es.jcelayardz.ecommercerestapi.repository.ProductRepository;
import es.jcelayardz.ecommercerestapi.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final StoreRepository storeRepository;


    public ProductService(ProductRepository productRepository, StoreRepository storeRepository) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAllVisible()
                .stream()
                .map(p -> p.toDto())
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(Integer productId) {
        return productRepository.findById(productId)
                .map(p -> p.toDto())
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }

    public ProductDto saveProduct(ProductDto productDto) {
        Store store = storeRepository.findByName(productDto.getStoreName())
                .orElseThrow(() -> new ProductBadRequestException("Could not found store " + productDto.getStoreName()));

        Product product = productDto.toEntity();
        product.setStore(store);

        return productRepository.save(product).toDto();
    }

    public ProductDto updateProduct(Integer productId, ProductDto productDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        if (productDto.getDescription() != null) {
            product.setDescription(productDto.getDescription());
        }
        if (productDto.isVisible() != null) {
            product.setVisible(productDto.isVisible());
        }

        return productRepository.save(product).toDto();
    }

    public void deleteProduct(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        productRepository.delete(product);
    }
}
