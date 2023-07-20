package es.jcelayardz.ecommercerestapi.service;

import es.jcelayardz.ecommercerestapi.exception.ProductNotFoundException;
import es.jcelayardz.ecommercerestapi.model.Product;
import es.jcelayardz.ecommercerestapi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    public final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }
}
