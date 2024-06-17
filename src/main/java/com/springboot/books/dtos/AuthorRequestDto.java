package com.springboot.books.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public record AuthorRequestDto(@NotBlank String fullName,
                               @NotBlank String country,
                               @NotBlank String city,
                               @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthdate){

}
