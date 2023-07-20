package es.jcelayardz.ecommercerestapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productid", nullable = false, unique = true)
    @NotNull
    private Integer productId;

    @Column(nullable = false, length = 30)
    @NotNull
    private String name;

    @Column(nullable = false)
    @NotNull
    private Float price;

    @Column(length = 100)
    private String description;

    @Column(name = "isvisible", nullable = false)
    private boolean isVisible;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    }, optional = false)
    @JoinColumn(name = "storename", referencedColumnName = "name")
    private Store store;

    public Product() {
    }

    public Product(String name, Float price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.isVisible = true;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
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

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "Product = {\n" + "productId=" + productId + ", name='" + name + '\'' + ", price=" + price + ", description='" + description + '\'' + ", isVisible=" + isVisible + "}\n";
    }
}
