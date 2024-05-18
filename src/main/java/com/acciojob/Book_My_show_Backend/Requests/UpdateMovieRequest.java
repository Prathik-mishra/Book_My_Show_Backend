package com.acciojob.Book_My_show_Backend.Requests;

import com.acciojob.Book_My_show_Backend.Enums.Language;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateMovieRequest {
    private String movieName;
    private Language newLanguage;
    private Double newRating;
}
