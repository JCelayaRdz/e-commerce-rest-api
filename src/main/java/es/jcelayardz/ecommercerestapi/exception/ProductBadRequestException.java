package es.jcelayardz.ecommercerestapi.exception;

public class ProductBadRequestException extends RuntimeException {
    public ProductBadRequestException(String message) {
        super(message);
    }
}
