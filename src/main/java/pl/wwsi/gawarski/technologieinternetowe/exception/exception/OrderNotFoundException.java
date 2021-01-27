package pl.wwsi.gawarski.technologieinternetowe.exception.exception;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(String orderNumber) {
        super("Order " + orderNumber + " does not exists");
    }
}
