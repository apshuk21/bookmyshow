package com.example.bookmyshow.models;

import com.example.bookmyshow.models.constants.Language;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Movie extends BaseModel {
    private String title;
    private String director;
    private String year;
    private String genre;

    // This is again a Many-to-Many relation
    @ElementCollection
    private List<String> actors;

    // This is a Many-to-Many relationship.
    // Enums can't be declared as entities, so we need Element Collection
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Language> languages;

    private LocalDateTime releaseDate;
    private double length;
}
