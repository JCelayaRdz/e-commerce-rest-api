package es.jcelayardz.ecommercerestapi.controller;

import es.jcelayardz.ecommercerestapi.dto.AdminDto;
import es.jcelayardz.ecommercerestapi.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admins")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/{username}")
    public AdminDto getAdminByUsername(@PathVariable String username) {
        return adminService.getAdminByUsername(username);
    }

    @PostMapping
    public AdminDto saveAdmin(@Valid @RequestBody AdminDto adminDto) {
        return adminService.saveAdmin(adminDto);
    }

    @DeleteMapping("/{username}")
    public void deleteAdmin(@PathVariable String username) {
        adminService.deleteAdmin(username);
    }
}
