package com.example.bookmyshow.models;

import com.example.bookmyshow.models.constants.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel {
    private int amount;

    @OneToMany
    private List<Payment> payments;

    @ManyToOne
    private User bookedBy;

    private LocalDateTime bookingDate;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @OneToMany
    private List<ShowSeat> showSeats;
}
