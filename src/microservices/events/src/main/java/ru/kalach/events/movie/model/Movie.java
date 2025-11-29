package ru.kalach.events.movie.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Movie {
    private String name;
    private int duration;
}
