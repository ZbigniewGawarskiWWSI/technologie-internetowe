package pl.wwsi.gawarski.technologieinternetowe.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ADDRESSES")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "POST_CODE", nullable = false)
    private String postCode;

    @Column(name = "STREET", nullable = false)
    private String street;

    @Column(name = "PROPERTY_NUMBER", nullable = false)
    private String propertyNumber;

    @Column(name = "LOCAL_NUMBER")
    private String localNumber;

    public Address(String city, String postCode, String street, String propertyNumber, String localNumber) {
        this.city = city;
        this.postCode = postCode;
        this.street = street;
        this.propertyNumber = propertyNumber;
        this.localNumber = localNumber;
    }
}
