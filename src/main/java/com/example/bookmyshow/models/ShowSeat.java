package com.example.bookmyshow.models;

import com.example.bookmyshow.models.constants.ShowSeatStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
public class ShowSeat extends BaseModel {
    @ManyToOne
    private Show show;

    @ManyToOne
    private Seat seat;

    private LocalDateTime blockedAt;

    @Enumerated(EnumType.STRING)
    private ShowSeatStatus showSeatStatus;
}
