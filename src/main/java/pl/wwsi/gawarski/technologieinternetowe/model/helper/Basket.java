package pl.wwsi.gawarski.technologieinternetowe.model.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.wwsi.gawarski.technologieinternetowe.dto.DishDTO;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Basket {
    private List<DishDTO> dishes;
    private double price;

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

}
