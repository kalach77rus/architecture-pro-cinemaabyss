package ru.kalach.events.movie.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentEvent {
    private int paymentId;
    private int userId;
    private Double amount;
    private String status;
    private String timestamp;
    private String methodType;
}
