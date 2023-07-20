package es.jcelayardz.ecommercerestapi.dto;

import es.jcelayardz.ecommercerestapi.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductDto {

    private Integer productId;

    @NotBlank
    private String name;

    @NotNull
    private Float price;

    private String description;

    @NotBlank
    private String storeName;

    public ProductDto(Integer productId, String name, Float price, String description, String storeName) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.storeName = storeName;
    }

    public ProductDto(String name, Float price, String description, String storeName) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.storeName = storeName;
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

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Product toEntity() {
        return new Product(
            this.name,
            this.price,
            this.description
        );
    }

    @Override
    public String toString() {
        return "ProductDto = \n{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", storeName='" + storeName + '\'' +
                "\n}";
    }
}
