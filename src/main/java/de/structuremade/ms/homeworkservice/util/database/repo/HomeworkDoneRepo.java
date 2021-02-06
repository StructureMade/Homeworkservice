package de.structuremade.ms.homeworkservice.util.database.repo;

import de.structuremade.ms.homeworkservice.util.database.entity.HomeworkDone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkDoneRepo extends JpaRepository<HomeworkDone, String> {
}
