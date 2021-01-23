package de.structuremade.ms.homeworkservice.api.json;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditHomework {

    private String title;

    private String description;

    private String date;

    private String lesson;
}
