package ru.kalach.events.movie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {
    private String originalName;
    private int duration;
    private String status;
    private long processedAt;
}
