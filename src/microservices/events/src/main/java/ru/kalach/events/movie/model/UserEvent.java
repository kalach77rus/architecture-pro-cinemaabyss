package ru.kalach.events.movie.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserEvent {
    private int id;
    private String username;
    private String action;
    private String timestamp;
}
