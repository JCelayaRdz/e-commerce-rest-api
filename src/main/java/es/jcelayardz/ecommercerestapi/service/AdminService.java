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

    public AdminDto saveAdmin(AdminDto adminDto) {
        Admin admin = adminDto.toEntity();
        return adminRepository.save(admin).toDto();
    }

    public void deleteAdmin(String username) {
        Admin admin = adminRepository.findById(username)
                .orElseThrow(() -> new AdminNotFoundException(username));

        adminRepository.delete(admin);
    }
}
