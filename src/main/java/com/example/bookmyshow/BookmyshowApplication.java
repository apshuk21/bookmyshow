package com.example.bookmyshow;

import com.example.bookmyshow.controllers.UserController;
import com.example.bookmyshow.dtos.SignupRequestDTO;
import com.example.bookmyshow.dtos.SignupResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookmyshowApplication implements CommandLineRunner {
    @Autowired
    UserController userController;

    public static void main(String[] args) {
        SpringApplication.run(BookmyshowApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        SignupRequestDTO signupRequestDTO = new SignupRequestDTO();

        signupRequestDTO.setFirstName("John");
        signupRequestDTO.setLastName("Doe");
        signupRequestDTO.setEmail("john@doe.com");
        signupRequestDTO.setPassword("password");
        signupRequestDTO.setUserName("john_doe");

        SignupResponseDTO signupResponseDTO = userController.signup(signupRequestDTO);

        System.out.println(signupResponseDTO.getUserId());
        System.out.println(signupResponseDTO.getMessage());
    }
}
