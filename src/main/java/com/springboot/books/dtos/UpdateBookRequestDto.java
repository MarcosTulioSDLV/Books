package com.springboot.books.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record UpdateBookRequestDto(@NotBlank String title,
                                   @NotBlank String edition,
                                   @PositiveOrZero Integer publicationYear) {
}
