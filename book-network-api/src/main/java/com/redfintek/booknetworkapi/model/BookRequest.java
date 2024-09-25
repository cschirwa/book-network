package com.redfintek.booknetworkapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;


public record BookRequest(
        @NotNull(message = "Book title mandatory")
        @NotBlank(message = "Book title mandatory")
        String title,
        String isbn,
        List<Author> authors
) {
}
