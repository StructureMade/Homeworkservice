package de.structuremade.ms.homeworkservice.util.database.repo;

import de.structuremade.ms.homeworkservice.util.database.entity.Homework;
import de.structuremade.ms.homeworkservice.util.database.entity.HomeworkDone;
import de.structuremade.ms.homeworkservice.util.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkDoneRepo extends JpaRepository<HomeworkDone, String> {
}
