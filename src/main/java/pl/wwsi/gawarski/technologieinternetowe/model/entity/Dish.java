package pl.wwsi.gawarski.technologieinternetowe.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DISHES")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = DishType.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private DishType dishType;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private double price;
}
