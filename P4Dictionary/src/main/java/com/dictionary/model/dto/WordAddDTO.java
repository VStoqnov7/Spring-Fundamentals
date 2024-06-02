package com.dictionary.model.dto;

import com.dictionary.model.entity.Language;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class WordAddDTO {

    @NotNull
    @Size(min = 2, max = 40,message = "The term length must be between 2 and 40 characters!")
    private String term;

    @NotNull
    @Size(min = 2, max = 80,message = "The term length must be between 2 and 80 characters!")
    private String translation;

    @Size(min = 2, max = 200,message = "The term length must be between 2 and 200 characters!")
    private String example;

    @NotNull
    @PastOrPresent(message = "The input date must be in the past or present!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inputDate;

    @NotNull
    @NotEmpty(message = "You must select a language!")
    private String language;
}
