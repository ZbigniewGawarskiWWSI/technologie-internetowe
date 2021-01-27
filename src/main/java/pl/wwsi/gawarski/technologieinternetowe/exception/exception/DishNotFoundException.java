package pl.wwsi.gawarski.technologieinternetowe.exception.exception;

public class DishNotFoundException extends Exception {
    public DishNotFoundException(Long id) {
        super("Dish " + id + " does not exist");
    }
}
