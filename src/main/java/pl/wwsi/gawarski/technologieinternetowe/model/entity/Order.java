package pl.wwsi.gawarski.technologieinternetowe.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wwsi.gawarski.technologieinternetowe.util.UuidFactory;

import javax.persistence.*;
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

    @Column(name = "ORDER_NUMBER")
    private String orderNumber;

    @ManyToMany(targetEntity = Dish.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @Column(name = "DISHES")
    private List<Dish> dishes;

    @OneToOne(targetEntity = Address.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Address address;

    @OneToOne(targetEntity = Person.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Person person;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;

    @Column(name = "DELIVERY_DATE")
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
