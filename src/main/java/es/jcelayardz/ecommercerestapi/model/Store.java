package es.jcelayardz.ecommercerestapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "store")
public class Store {

    @Id
    @Column(nullable = false, unique = true, length = 40)
    private String name;

    @Column(length = 200)
    private String description;

    @Column(name = "isvisible", nullable = false)
    private boolean isVisible;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "adminusername", referencedColumnName = "username")
    @JsonIgnore
    private Admin admin;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private Set<Product> products;

    public Store() {
    }

    public Store(String name, String description) {
        this.name = name;
        this.description = description;
        this.isVisible=true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Store = {\n" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isVisible=" + isVisible +
                "}\n";
    }
}
