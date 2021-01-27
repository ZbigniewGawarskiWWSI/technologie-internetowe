package pl.wwsi.gawarski.technologieinternetowe.model.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.wwsi.gawarski.technologieinternetowe.dto.DishDTO;

import java.util.ArrayList;
import java.util.List;


@Getter
@Repository
public class Basket {
    private List<DishDTO> dishes;
    private double price;

    public Basket() {
        this.dishes = new ArrayList<>();
        this.price = 0;
    }

    private void calculatePrice() {
        this.price = 0;
        for (DishDTO dish : dishes) {
            price += dish.getPrice();
        }
    }

    public void addDish(DishDTO dishDTO) {
        dishes.add(dishDTO);
        calculatePrice();
    }

    public void addDishes(DishDTO dishDTO, int number) {
        for(int i=0;i<number;i++){
            dishes.add(dishDTO);
        }
        calculatePrice();
    }

    public void removeDish(DishDTO dishDTO) {
        dishes.remove(dishDTO);
        calculatePrice();
    }

}
