package es.jcelayardz.ecommercerestapi.service;

import es.jcelayardz.ecommercerestapi.dto.AdminDto;
import es.jcelayardz.ecommercerestapi.exception.AdminNotFoundException;
import es.jcelayardz.ecommercerestapi.model.Admin;
import es.jcelayardz.ecommercerestapi.repository.AdminRepository;

public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public AdminDto getAdminByUsername(String username) {
        return adminRepository.findById(username)
                .map(Admin::toDto)
                .orElseThrow(() -> new AdminNotFoundException(username));

    }
}