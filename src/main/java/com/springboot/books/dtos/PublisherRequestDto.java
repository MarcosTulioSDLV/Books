package com.springboot.books.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record PublisherRequestDto(@NotBlank String name,
                                  @NotBlank String country,
                                  @NotBlank String city,
                                  @PositiveOrZero Integer foundedYear,
                                  @NotBlank @Email String email) {
}
