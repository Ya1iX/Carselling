package com.practice.carselling.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "ads")
public class Ad {

    @Id
    @SequenceGenerator(
            name = "ads_sequence",
            sequenceName = "ads_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ads_sequence"
    )
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    @NotNull(message = "Author cannot be null")
    private User author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id", nullable = false)
    @NotNull(message = "Car cannot be null")
    private Car car;

    @Column(nullable = false)
//    @NotNull(message = "TimeStamp cannot be null")
    private LocalDateTime timeStamp;

    @Column(nullable = false)
    @NotNull(message = "Price cannot be null")
    private Double price;

    @Column(nullable = false)
    @NotBlank(message = "City cannot be empty or null")
    private String city;

    @Column(nullable = false)
    @NotBlank(message = "Phone number cannot be empty or null")
    private String phoneNumber;

    @Column(nullable = false)
    @NotNull(message = "Deleted flag cannot be null")
    private Boolean isDeleted;

    private String description;

    public Ad(User author, Car car, LocalDateTime timeStamp, Double price, String city, String phoneNumber, Boolean isDeleted, String description) {
        this.author = author;
        this.car = car;
        this.timeStamp = timeStamp;
        this.price = price;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.isDeleted = isDeleted;
        this.description = description;
    }

    @JsonCreator //Constructor for object initialization with auto-timestamp
    public Ad(User author, Car car, Double price, String city, String phoneNumber, Boolean isDeleted, String description) {
        this.author = author;
        this.car = car;
        this.timeStamp = LocalDateTime.now();
        this.price = price;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.isDeleted = isDeleted;
        this.description = description;
    }
}
