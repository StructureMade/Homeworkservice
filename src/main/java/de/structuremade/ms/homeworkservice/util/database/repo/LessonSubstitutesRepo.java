package de.structuremade.ms.homeworkservice.util.database.repo;

import de.structuremade.ms.homeworkservice.util.database.entity.LessonRoles;
import de.structuremade.ms.homeworkservice.util.database.entity.LessonSubstitutes;
import de.structuremade.ms.homeworkservice.util.database.entity.Lessons;
import de.structuremade.ms.homeworkservice.util.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonSubstitutesRepo extends JpaRepository<LessonSubstitutes, String> {
    boolean existsBySubstituteTeacherAndLesson(User user, Lessons lessons);
}
