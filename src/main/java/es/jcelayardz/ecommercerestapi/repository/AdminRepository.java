package es.jcelayardz.ecommercerestapi.repository;

import es.jcelayardz.ecommercerestapi.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, String> {

    @Query("SELECT a FROM Admin a WHERE a.username = ?1 AND a.adminType = 'STORE'")
    Optional<Admin> findStoreAdminByUsername(String username);
}
