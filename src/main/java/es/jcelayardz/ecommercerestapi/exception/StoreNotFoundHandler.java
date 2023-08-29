package es.jcelayardz.ecommercerestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class StoreNotFoundHandler {

    @ResponseBody
    @ExceptionHandler(StoreNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpProblem storeNotFoundHandler(StoreNotFoundException e) {
        return new HttpProblem(
                "https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/404",
                "NOT FOUND",
                404,
                e.getMessage(),
                "about:blank"
        );
    }
}
