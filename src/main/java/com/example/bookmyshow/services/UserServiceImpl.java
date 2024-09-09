package com.example.bookmyshow.services;

import com.example.bookmyshow.exceptions.UserAlreadyExistsException;
import com.example.bookmyshow.exceptions.UserNotFoundException;
import com.example.bookmyshow.models.User;
import com.example.bookmyshow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User signUp(String email, String password, String firstName, String lastName, String userName) throws UserAlreadyExistsException {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }

        // Otherwise create the user
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(userName);
        user.setBookings(new ArrayList<>());

        return userRepository.save(user);
    }
}
