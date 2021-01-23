package de.structuremade.ms.homeworkservice.api.json.answer;

import de.structuremade.ms.homeworkservice.util.database.entity.Homework;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetMyHomework {
    private List<Homework> homework;
}
