package pl.wwsi.gawarski.technologieinternetowe.model.entity;

import lombok.Getter;
import lombok.Setter;
import pl.wwsi.gawarski.technologieinternetowe.util.UuidFactory;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ORDER_NUMBER", nullable = false)
    private String orderNumber;

    @ManyToMany(targetEntity = Dish.class, fetch = FetchType.LAZY)
    @Column(name = "DISHES", nullable = false)
    private List<Dish> dishes;

    @NotNull
    @OneToOne(targetEntity = Address.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Address address;

    @NotNull
    @OneToOne(targetEntity = Person.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Person person;

    @Column(name = "PRICE", nullable = false)
    private double price;

    @Column(name = "CREATION_DATE", nullable = false)
    private LocalDateTime creationDate;


    @Column(name = "DELIVERY_DATE", nullable = false)
    private LocalDateTime deliveryDate;


    public Order(List<Dish> dishes, Address address, Person person, double price, LocalDateTime creationDate, LocalDateTime deliveryDate) {
        this.orderNumber = UuidFactory.generateUUID();
        this.dishes = dishes;
        this.address = address;
        this.person = person;
        this.price = price;
        this.creationDate = creationDate;
        this.deliveryDate = deliveryDate;
    }
}
