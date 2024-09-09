package com.example.bookmyshow.models;

import com.example.bookmyshow.models.constants.Feature;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
public class Screen extends BaseModel {
    private String name;

    @OneToMany
    private List<Seat> seats;

    @ManyToOne
    private Theatre theatre;

    // This is again a Many-to-Many relation
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Feature> features;

    private int dimension;
}
