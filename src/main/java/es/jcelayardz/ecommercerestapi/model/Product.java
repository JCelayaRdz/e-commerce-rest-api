package es.jcelayardz.ecommercerestapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productid", nullable = false, unique = true)
    private Integer productId;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false)
    private float price;

    @Column(length = 100)
    private String description;

    @Column(nullable = false)
    private boolean isVisible;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    }, optional = false)
    @JoinColumn(name = "storeName", referencedColumnName = "name")
    private Store store;

    public Product() {
    }

    public Product(String name, float price, String description) {
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
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
