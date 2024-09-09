package com.example.bookmyshow.services;

import com.example.bookmyshow.exceptions.ShowNotFoundException;
import com.example.bookmyshow.exceptions.ShowSeatNotAvailableException;
import com.example.bookmyshow.exceptions.UserNotFoundException;
import com.example.bookmyshow.models.Booking;
import com.example.bookmyshow.models.Show;
import com.example.bookmyshow.models.ShowSeat;
import com.example.bookmyshow.models.User;
import com.example.bookmyshow.models.constants.BookingStatus;
import com.example.bookmyshow.repository.BookingRepository;
import com.example.bookmyshow.repository.ShowRepository;
import com.example.bookmyshow.repository.ShowSeatRepository;
import com.example.bookmyshow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ShowRepository showRepository;
    private final SynchronizedBookingService synchronizedBookingService;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, UserRepository userRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository, SynchronizedBookingService synchronizedBookingService) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.synchronizedBookingService = synchronizedBookingService;
    }

    @Override
    public Booking createBooking(List<Long> showSeatIds, Long showId, Long userId) throws UserNotFoundException, ShowNotFoundException, ShowSeatNotAvailableException {
        // User authentication
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

        // Get the show using show id
        Show show = showRepository.findById(showId).orElseThrow(() -> new ShowNotFoundException("Show not found"));

        List<ShowSeat> showSeats =  synchronizedBookingService.assignShowSeats(showSeatIds);

        // Calculate the price

        Booking booking = new Booking();
        booking.setBookedBy(user);
        booking.setBookingDate(LocalDateTime.now());
        booking.setBookingStatus(BookingStatus.SUCCESSFUL);
        booking.setAmount(120);
        booking.setShowSeats(showSeats);

        return bookingRepository.save(booking);
    }
}
