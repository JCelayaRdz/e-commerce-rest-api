package es.jcelayardz.ecommercerestapi.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Integer productId) {
        super("Could not found product with id " + productId);
    }
}
