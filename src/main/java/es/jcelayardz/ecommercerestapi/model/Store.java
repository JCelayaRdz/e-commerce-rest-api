package es.jcelayardz.ecommercerestapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "store")
public class Store {

    @Id
    @Column(nullable = false, unique = true, length = 40)
    private String name;

    @Column(length = 200)
    private String description;

    @Column(nullable = false)
    private boolean isVisible;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "adminUsername", referencedColumnName = "username")
    private Admin admin;

    public Store() {
    }

    public Store(String name, String description) {
        this.name = name;
        this.description = description;
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

    @Override
    public String toString() {
        return "Store = {\n" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isVisible=" + isVisible +
                "}\n";
    }
}
