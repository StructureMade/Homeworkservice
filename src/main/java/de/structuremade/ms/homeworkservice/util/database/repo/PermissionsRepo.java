package de.structuremade.ms.homeworkservice.util.database.repo;

import de.structuremade.ms.homeworkservice.util.database.entity.Homework;
import de.structuremade.ms.homeworkservice.util.database.entity.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionsRepo extends JpaRepository<Permissions, String> {
}
