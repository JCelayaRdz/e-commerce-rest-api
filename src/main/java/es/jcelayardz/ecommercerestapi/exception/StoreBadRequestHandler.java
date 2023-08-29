package es.jcelayardz.ecommercerestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class StoreBadRequestHandler {

    // TODO: Refactor all bad request handlers into a single method
    @ResponseBody
    @ExceptionHandler(StoreBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpProblem storeBadRequestHandler(StoreBadRequestException e) {
        return new HttpProblem (
                "https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/400",
                "BAD REQUEST",
                400,
                e.getMessage(),
                "about:blank"
        );
    }
}
