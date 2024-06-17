package com.springboot.books.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record BookRequestDto(@NotBlank String title,
                             @NotBlank String edition,
                             @PositiveOrZero Integer publicationYear,
                             @Positive Long publisherId,
                             @NotEmpty List<@Positive Long> authorsIds) {

}
