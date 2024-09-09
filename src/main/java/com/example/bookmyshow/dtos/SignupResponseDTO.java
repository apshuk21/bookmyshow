package com.example.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupResponseDTO {
    private long userId;
    private ResponseStatus status;
    private String message;
}
