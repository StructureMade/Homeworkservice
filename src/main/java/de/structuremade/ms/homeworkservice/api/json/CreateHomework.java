package de.structuremade.ms.homeworkservice.api.json;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateHomework {

    @NotNull
    private String title;

    private String description;

    @NotNull
    private String date;

    @NotNull
    private String lesson;
}
