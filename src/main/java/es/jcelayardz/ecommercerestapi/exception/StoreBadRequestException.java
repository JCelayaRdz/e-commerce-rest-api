package es.jcelayardz.ecommercerestapi.exception;

public class StoreBadRequestException extends RuntimeException {
    public StoreBadRequestException(String adminUsername) {
        super("Could not find a store admin with username " + adminUsername);
    }
}
