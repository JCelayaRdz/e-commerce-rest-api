package es.jcelayardz.ecommercerestapi.repository;

import es.jcelayardz.ecommercerestapi.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {
}
