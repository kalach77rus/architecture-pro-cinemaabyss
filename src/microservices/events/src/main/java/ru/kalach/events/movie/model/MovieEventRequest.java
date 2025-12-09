package ru.kalach.events.movie.model;

import jakarta.validation.constraints.*;

public record MovieEventRequest(
    @NotNull int movie_id,
    @NotBlank String title,
    @NotBlank String action,
    @NotNull int user_id
) {}