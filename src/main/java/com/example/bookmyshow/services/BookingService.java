package com.example.bookmyshow.services;

import com.example.bookmyshow.exceptions.ShowNotFoundException;
import com.example.bookmyshow.exceptions.ShowSeatNotAvailableException;
import com.example.bookmyshow.exceptions.UserNotFoundException;
import com.example.bookmyshow.models.Booking;

import java.util.List;

public interface BookingService {
    Booking createBooking(List<Long> showSeatIds, Long showId, Long userId) throws UserNotFoundException, ShowNotFoundException, ShowSeatNotAvailableException;
}
