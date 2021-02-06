package de.structuremade.ms.homeworkservice.util.database.repo;

import de.structuremade.ms.homeworkservice.util.database.entity.LessonRoles;
import de.structuremade.ms.homeworkservice.util.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
    boolean existsByIdAndLessonRoles(String s, LessonRoles homework);
}
