package de.structuremade.ms.homeworkservice.util.database.repo;

import de.structuremade.ms.homeworkservice.util.database.entity.Lessons;
import de.structuremade.ms.homeworkservice.util.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonsRepo extends JpaRepository<Lessons, String> {
    boolean existsByTeacherAndId(User teacher, String lesson);
}
