package es.jcelayardz.ecommercerestapi.exception;

public class AdminNotFoundException extends RuntimeException {
    public AdminNotFoundException(String username) {
        super("Could not find admin with username: " + username);
    }
}
