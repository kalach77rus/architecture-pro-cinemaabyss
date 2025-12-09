package ru.kalach.events.movie.model;

import jakarta.validation.constraints.*;

public record PaymentEventRequest(
    @NotNull int payment_id,
    @NotNull int user_id,
    @NotNull Double amount,
    @NotBlank String status,
    @NotBlank String timestamp,
    @NotBlank String method_type
) {}