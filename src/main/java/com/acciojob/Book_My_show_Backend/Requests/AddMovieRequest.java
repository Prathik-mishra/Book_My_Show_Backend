package com.acciojob.Book_My_show_Backend.Requests;

import com.acciojob.Book_My_show_Backend.Enums.Language;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data

public class AddMovieRequest {

    @Column(unique = true)
    private String movieName;

    private Double duration;

    private LocalDate releaseDate;

    @Enumerated(value = EnumType.STRING)
    private Language language;

    private Double rating;
}
