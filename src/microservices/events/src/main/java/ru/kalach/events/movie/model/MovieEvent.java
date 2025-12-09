package ru.kalach.events.movie.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieEvent {
    private int id;
    private String name;
    private String action;
    private int userId;
}
