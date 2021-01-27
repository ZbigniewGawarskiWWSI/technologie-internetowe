package pl.wwsi.gawarski.technologieinternetowe.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ORDER_NUMBER")
    private String orderNumber;

    @ManyToMany(targetEntity = Dish.class, fetch = FetchType.LAZY)
    @Column(name = "DISHES")
    private List<Dish> dishes;

    @OneToOne
    private Address address;

    @OneToOne
    private Person person;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;

    @Column(name = "DELIVERY_DATE")
    private LocalDateTime deliveryDate;

    public Order(List<Dish> dishes, String orderNumber, double price, Address address) {
        this.dishes = dishes;
        this.orderNumber = orderNumber;
        this.price = price;
        this.address = address;
    }

    public Order(String orderNumber, List<Dish> dishes, Address address, double price, LocalDateTime creationDate, LocalDateTime deliveryDate) {
        this.orderNumber = orderNumber;
        this.dishes = dishes;
        this.address = address;
        this.price = price;
        this.creationDate = creationDate;
        this.deliveryDate = deliveryDate;
    }
}
