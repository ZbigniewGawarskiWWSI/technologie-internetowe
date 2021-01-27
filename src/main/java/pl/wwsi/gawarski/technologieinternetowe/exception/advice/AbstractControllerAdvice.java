package pl.wwsi.gawarski.technologieinternetowe.exception.advice;

public abstract class AbstractControllerAdvice {

    protected String handler(Exception exception) {
        return exception.getMessage();
    }
}
