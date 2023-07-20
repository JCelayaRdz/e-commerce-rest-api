package es.jcelayardz.ecommercerestapi.exception;

public record HttpProblem(String type,
                          String title,
                          Integer status,
                          String detail,
                          String instance) {
}
