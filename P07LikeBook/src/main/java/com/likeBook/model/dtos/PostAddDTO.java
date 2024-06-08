package com.likeBook.model.dtos;

import com.likeBook.model.enums.MoodName;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostAddDTO {

    @NotNull
    @Size(min = 2, max = 50, message = "Content length must be between 2 and 150 characters!")
    private String content;

    @NotNull(message = "You must select a mood!")
    private MoodName mood;
}
