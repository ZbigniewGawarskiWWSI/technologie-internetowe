package pl.wwsi.gawarski.technologieinternetowe.model.helper;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import pl.wwsi.gawarski.technologieinternetowe.dto.DishDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Getter
public class Basket {
    private List<DishDTO> dishList;
    private double price;
    private Map<DishDTO, Integer> dishMap;

    public Basket() {
        this.dishList = new ArrayList<>();
        this.dishMap = new HashMap<>();
        this.price = 0;
    }

    private void calculatePrice() {
        this.price = 0;
        for (DishDTO dish : dishList) {
            price += dish.getPrice();
        }
    }

    private void addDish(DishDTO dishDTO) {
        dishList.add(dishDTO);
    }

    public void addDishes(DishDTO dishDTO, int number) {
        int newNumber;
        if (dishMap.containsKey(dishDTO)) {
            newNumber = dishMap.get(dishDTO);
            newNumber += number;
            dishMap.replace(dishDTO, newNumber);
        } else {
            newNumber = number;
            dishMap.put(dishDTO, newNumber);
        }
        for (int i = 0; i < number; i++) {
            addDish(dishDTO);
        }
        calculatePrice();
    }

    public void removeDish(DishDTO dishDTO) {
        int number = dishMap.get(dishDTO);
        dishMap.remove(dishDTO);
        for (int i = 0; i < number; i++) {
            dishList.remove(dishDTO);
        }
        calculatePrice();
    }

}
