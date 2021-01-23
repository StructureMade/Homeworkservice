package de.structuremade.ms.homeworkservice.util.database.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "lessonroles")
@Getter
@Setter
public class LessonRoles {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private String id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "schoolid", foreignKey = @ForeignKey(name = "fk_schoolid"))
    private School school;

    @OneToMany(targetEntity = Lessons.class)
    @JoinColumn(name = "lessonroleid")
    private List<Lessons> lessons;

    @OneToMany(targetEntity = Homework.class)
    @JoinColumn(name = "lesson", foreignKey = @ForeignKey(name = "fk_lessonrole"))
    private List<Homework> homework;
}
