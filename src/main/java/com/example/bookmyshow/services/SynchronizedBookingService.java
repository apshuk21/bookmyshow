package com.example.bookmyshow.services;

import com.example.bookmyshow.exceptions.ShowSeatNotAvailableException;
import com.example.bookmyshow.models.ShowSeat;
import com.example.bookmyshow.models.constants.ShowSeatStatus;
import com.example.bookmyshow.repository.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SynchronizedBookingService {

    private final ShowSeatRepository showSeatRepository;

    @Autowired
    public SynchronizedBookingService(ShowSeatRepository showSeatRepository) {
        this.showSeatRepository = showSeatRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<ShowSeat> assignShowSeats(List<Long> showSeatIds) throws ShowSeatNotAvailableException {
        // Get the show seats from the list of show seat ids
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        /*
            Check all seats availability.
            if available, mark seats as blocked
            or else return exception
         */

        for (ShowSeat showSeat : showSeats) {
            if (!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) {
                throw new ShowSeatNotAvailableException("Show Seat is not available!");
            }

            /*
            Very important
            Why not to book the seat in this loop.
            Because, seat booking operation is atomic. Either all seats should be booked or none.
             */
        }

        for (ShowSeat showSeat : showSeats) {
            // Change the seat status as booked
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            showSeat.setBlockedAt(LocalDateTime.now());
        }

        return showSeatRepository.saveAll(showSeats);
    }
}
