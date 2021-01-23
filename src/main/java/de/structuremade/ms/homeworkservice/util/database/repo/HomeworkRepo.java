package de.structuremade.ms.homeworkservice.util.database.repo;

import de.structuremade.ms.homeworkservice.util.database.entity.Homework;
import de.structuremade.ms.homeworkservice.util.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkRepo extends JpaRepository<Homework, String> {
    boolean existsByCreatorAndId(User one, String homework);
}
