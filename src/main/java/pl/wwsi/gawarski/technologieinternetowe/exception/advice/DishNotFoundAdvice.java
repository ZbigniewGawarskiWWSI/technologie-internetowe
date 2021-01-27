package pl.wwsi.gawarski.technologieinternetowe.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.wwsi.gawarski.technologieinternetowe.exception.exception.DishNotFoundException;

@RestControllerAdvice
public class DishNotFoundAdvice extends AbstractControllerAdvice {

    @ResponseBody
    @ExceptionHandler(DishNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String dishNotFoundHandler(DishNotFoundException exception) {
        return handler(exception);
    }

}
