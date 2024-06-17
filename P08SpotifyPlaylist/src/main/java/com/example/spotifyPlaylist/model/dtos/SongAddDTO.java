package com.example.spotifyPlaylist.model.dtos;

import com.example.spotifyPlaylist.model.enums.StyleName;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SongAddDTO {

    @NotNull
    @Size(min = 3, max = 20, message = "Performer length must be between 3 and 20 characters! (inclusive 3 and 20).")
    private String performer;

    @NotNull
    @Size(min = 2, max = 20, message = "Title length must be between 2 and 20 characters! (inclusive 2 and 20).")
    private String title;

    @NotNull(message = "Duration cannot be empty!")
    @Positive(message = "Duration must be positive!")
    private Integer duration;

    @NotNull(message = "Release Date cannot be empty!")
    @Future(message = "Release Date must be in future!")
    private LocalDate releaseDate;

    @NotNull(message = "You must select a style!")
    private StyleName style;
}
