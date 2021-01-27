package pl.wwsi.gawarski.technologieinternetowe.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.wwsi.gawarski.technologieinternetowe.exception.exception.OrderNotFoundException;

@RestControllerAdvice
public class OrderNotFoundAdvice extends AbstractControllerAdvice {

    @ResponseBody
    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String orderNotFoundHandler(OrderNotFoundException exception) {
        return handler(exception);
    }

}
