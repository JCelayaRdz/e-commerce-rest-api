package es.jcelayardz.ecommercerestapi.exception;

public class StoreNotFoundException extends RuntimeException {
    public StoreNotFoundException(String storeName) {
        super("Could not found store with name " + storeName);
    }
}
