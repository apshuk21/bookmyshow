package com.example.bookmyshow.controllers;

import com.example.bookmyshow.dtos.ResponseStatus;
import com.example.bookmyshow.dtos.SignupRequestDTO;
import com.example.bookmyshow.dtos.SignupResponseDTO;
import com.example.bookmyshow.models.User;
import com.example.bookmyshow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SignupResponseDTO signup(SignupRequestDTO signupRequestDTO) {
        SignupResponseDTO signupResponseDTO = new SignupResponseDTO();

        try {
            User user = userService.signUp(signupRequestDTO.getEmail(), signupRequestDTO.getPassword(), signupRequestDTO.getFirstName(), signupRequestDTO.getLastName(), signupRequestDTO.getUserName());
            signupResponseDTO.setUserId(user.getId());
            signupResponseDTO.setStatus(ResponseStatus.SUCCESS);
            signupResponseDTO.setMessage("Successfully registered!");

        } catch (Exception e) {
            signupResponseDTO.setStatus(ResponseStatus.FAILURE);
            signupResponseDTO.setMessage(e.getMessage());
        }

        return signupResponseDTO;
    }
}
