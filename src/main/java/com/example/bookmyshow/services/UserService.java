package com.example.bookmyshow.services;

import com.example.bookmyshow.exceptions.UserAlreadyExistsException;
import com.example.bookmyshow.exceptions.UserNotFoundException;
import com.example.bookmyshow.models.User;

public interface UserService {
    User signUp(String email, String password, String firstName, String lastName, String userName) throws UserNotFoundException, UserAlreadyExistsException;
}
