package es.jcelayardz.ecommercerestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class ProductBadRequestHandler {

    @ResponseBody
    @ExceptionHandler(ProductBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpProblem productBadRequestHandler(ProductBadRequestException e) {
        return new HttpProblem (
                "https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/400",
                "BAD REQUEST",
                400,
                e.getMessage(),
                "about:blank"
        );
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpProblem validationErrorHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        /*If there are several attributes with the same validation annotation and the validation fails for both
         attributes the message will be repeated, that is why a set should be used instead of a list.*/
        Set<String> errorMessages = bindingResult.getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toSet());

        String errorMessage = "Validation errors: " + String.join("; ", errorMessages);

        return new HttpProblem(
                "https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/400",
                "BAD REQUEST",
                400,
                errorMessage,
                "about:blank"
        );
    }
}
