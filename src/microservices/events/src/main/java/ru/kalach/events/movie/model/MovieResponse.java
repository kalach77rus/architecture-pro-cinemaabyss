package ru.kalach.events.movie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {
    private int id;
    private String name;
    private String action;
    private int userId;
    private String status;
    private long processedAt;
}
