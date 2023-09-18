package es.jcelayardz.ecommercerestapi.dto;

import es.jcelayardz.ecommercerestapi.model.Admin;
import es.jcelayardz.ecommercerestapi.model.AdminType;
import jakarta.validation.constraints.NotBlank;

public class AdminDto {

    @NotBlank(message = "{admin.notblank}")
    private String username;

    @NotBlank(message = "{admin.notblank}")
    private String email;

    @NotBlank(message = "{admin.notblank}")
    private String password;

    @NotBlank(message = "{admin.admintype}")
    private AdminType adminType;

    private StoreDto store;

    public AdminDto(String username, String email, String password, AdminType adminType) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.adminType = adminType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AdminType getAdminType() {
        return adminType;
    }

    public void setAdminType(AdminType adminType) {
        this.adminType = adminType;
    }

    public StoreDto getStore() {
        return store;
    }

    public void setStore(StoreDto store) {
        this.store = store;
    }

    public Admin toEntity() {
        return new Admin (
                this.username,
                this.email,
                this.password,
                this.adminType
        );
    }

    @Override
    public String toString() {
        return "AdminDto = {\n" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", adminType=" + adminType +
                "\n";
    }
}
