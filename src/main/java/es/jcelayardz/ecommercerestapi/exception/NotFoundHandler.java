package es.jcelayardz.ecommercerestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotFoundHandler {

    @ResponseBody
    @ExceptionHandler({
            ProductNotFoundException.class,
            StoreNotFoundException.class,
            AdminNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpProblem notFoundHandler(RuntimeException e) {
        return new HttpProblem(
                "https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/404",
                "NOT FOUND",
                404,
                e.getMessage(),
                "about:blank"
        );
    }
}
