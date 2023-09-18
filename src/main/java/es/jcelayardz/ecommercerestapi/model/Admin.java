package es.jcelayardz.ecommercerestapi.model;

import es.jcelayardz.ecommercerestapi.dto.AdminDto;
import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @Column(nullable = false, unique = true, length = 25)
    private String username;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 70)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "admintype", nullable = false)
    private AdminType adminType;

    @OneToOne(mappedBy = "admin", cascade = CascadeType.ALL)
    private Store store;

    public Admin() {
    }

    public Admin(String username, String email, String password, AdminType adminType) {
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

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public AdminDto toDto() {
        AdminDto dto =  new AdminDto(
                this.username,
                this.email,
                this.password,
                this.adminType
        );
        dto.setStore(this.store.toDto());
        return dto;
    }

    @Override
    public String toString() {
        return "Admin = {\n" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", adminType=" + adminType +
                "}\n";
    }
}
