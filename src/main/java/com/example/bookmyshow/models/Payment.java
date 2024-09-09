package com.example.bookmyshow.models;

import com.example.bookmyshow.models.constants.PaymentGateway;
import com.example.bookmyshow.models.constants.PaymentMode;
import com.example.bookmyshow.models.constants.PaymentStatus;
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
public class Payment extends BaseModel {
    private int amount;
    private String refNumber;
    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private PaymentGateway paymentGateway;

    @ManyToOne
    private Booking booking;
}
