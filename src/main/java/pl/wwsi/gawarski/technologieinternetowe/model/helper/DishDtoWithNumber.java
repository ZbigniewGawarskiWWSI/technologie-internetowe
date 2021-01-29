package pl.wwsi.gawarski.technologieinternetowe.model.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wwsi.gawarski.technologieinternetowe.dto.DishDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishDtoWithNumber {
    private DishDTO dishDTO;
    private int number;
}
