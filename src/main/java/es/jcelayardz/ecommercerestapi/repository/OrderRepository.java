package es.jcelayardz.ecommercerestapi.repository;

import es.jcelayardz.ecommercerestapi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
