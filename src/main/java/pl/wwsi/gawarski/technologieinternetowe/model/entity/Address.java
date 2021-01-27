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
@Table(name = "ADDRESSES")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CITY")
    private String city;

    @Column(name = "POST_CODE")
    private String postCode;

    @Column(name = "STREET")
    private String street;

    @Column(name = "PROPERTY_NUMBER")
    private String propertyNumber;

    @Column(name = "LOCAL_NUMBER")
    private String localNumber;
}
