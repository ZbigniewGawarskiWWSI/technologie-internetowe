package pl.wwsi.gawarski.technologieinternetowe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.Dish;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishDTO {

    private Long id;

    private String type;

    private String name;

    private double price;

    public static DishDTO convertDishToDto(Dish dish) {
        DishDTO dishDTO = new DishDTO();
        dishDTO.setId(dish.getId());
        dishDTO.setName(dish.getName());
        dishDTO.setType(dish.getDishType().getName());
        dishDTO.setPrice(dish.getPrice());
        return dishDTO;
    }
}
