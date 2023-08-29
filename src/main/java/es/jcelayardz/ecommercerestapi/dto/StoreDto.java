package es.jcelayardz.ecommercerestapi.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class StoreDto {

    @NotBlank
    private String name;

    private String description;

    private boolean isVisible;

    @NotBlank
    private String adminUsername;

    private List<ProductDto> products;

    @JsonCreator
    public StoreDto(@JsonProperty String name,
                    @JsonProperty String description,
                    @JsonProperty String adminUsername) {
        this.name = name;
        this.description = description;
        this.isVisible = true;
        this.adminUsername = adminUsername;
        this.products = new ArrayList<>();
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "StoreDto {\n" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isVisible=" + isVisible +
                ", adminUsername='" + adminUsername + '\'' +
                "\n}";
    }
}
