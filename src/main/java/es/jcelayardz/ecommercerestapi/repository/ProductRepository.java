package es.jcelayardz.ecommercerestapi.repository;

import es.jcelayardz.ecommercerestapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
