package pl.wwsi.gawarski.technologieinternetowe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wwsi.gawarski.technologieinternetowe.dto.DishDTO;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.Dish;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.DishType;
import pl.wwsi.gawarski.technologieinternetowe.model.repository.DishRepo;
import pl.wwsi.gawarski.technologieinternetowe.model.repository.DishTypeRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishService {
    private DishRepo dishRepo;
    private DishTypeRepo dishTypeRepo;

    @Autowired
    public DishService(DishRepo dishRepo, DishTypeRepo dishTypeRepo) {
        this.dishRepo = dishRepo;
        this.dishTypeRepo = dishTypeRepo;
    }

    public List<Dish> getAll() {
        var dishes = dishRepo.findAll();
        List<Dish> dishesList = new ArrayList<Dish>();
        dishes.forEach(dishesList::add);
        return dishesList;
    }

    public List<DishDTO> getAllInDto() {
        var dishes = getAll();
        List<DishDTO> dtoList = new ArrayList<DishDTO>();
        for (Dish dish : dishes) {
            dtoList.add(DishDTO.convertDishToDto(dish));
        }
        return dtoList;
    }

    public void addDish(Dish dish) {
        dishRepo.save(dish);
    }

    public void addDishType(String dishTypeName) {
        DishType dishType = new DishType();
        dishType.setName(dishTypeName);
        dishTypeRepo.save(dishType);
    }
}
