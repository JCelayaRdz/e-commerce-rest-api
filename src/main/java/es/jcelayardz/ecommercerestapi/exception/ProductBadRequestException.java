package es.jcelayardz.ecommercerestapi.exception;

public class ProductBadRequestException extends RuntimeException {
    public ProductBadRequestException(String storeName) {
        super("Could not find a store with name " + storeName);
    }
}
