package de.structuremade.ms.homeworkservice.util.database.repo;

import de.structuremade.ms.homeworkservice.util.database.entity.LessonRoles;
import de.structuremade.ms.homeworkservice.util.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonrolesRepo extends JpaRepository<LessonRoles, String> {
    boolean existsByTeacherAndId(User user, String id);
}
