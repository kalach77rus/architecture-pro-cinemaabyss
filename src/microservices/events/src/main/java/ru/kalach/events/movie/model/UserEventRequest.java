package ru.kalach.events.movie.model;

import jakarta.validation.constraints.*;

public record UserEventRequest(
    @NotNull int user_id,
    @NotBlank String username,
    @NotBlank String action,
    @NotBlank String timestamp
) {}