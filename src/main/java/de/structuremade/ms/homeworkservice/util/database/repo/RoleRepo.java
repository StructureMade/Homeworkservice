package de.structuremade.ms.homeworkservice.util.database.repo;

import de.structuremade.ms.homeworkservice.util.database.entity.Homework;
import de.structuremade.ms.homeworkservice.util.database.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, String> {
}
