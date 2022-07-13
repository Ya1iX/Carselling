package com.practice.carselling.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(
        name = "cars",
        uniqueConstraints =  {
                @UniqueConstraint(
                        columnNames = {"id", "brand", "model", "body", "generation"},
                        name = "uk_cars")
        }
)
public class Car {

    @Id
    @SequenceGenerator(
            name = "cars_sequence",
            sequenceName = "cars_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cars_sequence"
    )
    private Long id;
    @Column(nullable = false)
    @NotBlank(message = "Brand cannot be empty")
    private String brand;

    @Column(nullable = false)
    @NotBlank(message = "Model cannot be empty")
    private String model;

    @Column(nullable = false)
    @NotBlank(message = "Body cannot be empty")
    private String body;

    private Short generation;

    public Car(String brand, String model, String body, Short generation) {
        this.brand = brand;
        this.model = model;
        this.body = body;
        this.generation = generation;
    }
}
