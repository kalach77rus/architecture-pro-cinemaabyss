package ru.kalach.events.movie.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private String username;
    private String email;
}
