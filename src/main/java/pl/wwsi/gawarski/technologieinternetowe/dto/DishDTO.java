package pl.wwsi.gawarski.technologieinternetowe.dto;

import lombok.*;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.Dish;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.DishType;
import pl.wwsi.gawarski.technologieinternetowe.model.repository.DishTypeRepo;

import java.util.ArrayList;
import java.util.List;

@ToString
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

    public static Dish convertDtoToDish(DishDTO dto, DishTypeRepo repo) {
        Dish dish = new Dish();
        dish.setId(dto.getId());
        dish.setName(dto.getName());
        dish.setPrice(dto.getPrice());
        DishType dishType = repo.findByName(dto.getName()).orElse(null);
        dish.setDishType(dishType);
        return dish;
    }

    public static List<Dish> convertDtosToDish(List<DishDTO> dtos, DishTypeRepo repo) {
        List<Dish> dishes = new ArrayList<>();
        for (DishDTO dto : dtos) {
            dishes.add(convertDtoToDish(dto, repo));
        }
        return dishes;
    }

}
