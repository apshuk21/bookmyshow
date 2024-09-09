package com.example.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity(name = "shows")
public class Show extends BaseModel {
    @ManyToOne
    private Movie movie;
    private LocalDateTime startTime;
    private int duration;

    @ManyToOne
    private Screen screen;

    @OneToMany
    private List<ShowSeat> showSeats;

    @OneToMany
    private List<ShowSeatType> showSeatTypes;
}
