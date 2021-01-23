package de.structuremade.ms.homeworkservice.util.database.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Lessons")
@Getter
@Setter
public class Lessons {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private String id;

    @Column
    private String day;

    @Column
    private String room;

    @Column
    private int state;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "teacherId", foreignKey = @ForeignKey(name = "fk_teacherid"))
    private User teacher;

    @ManyToOne(targetEntity = School.class)
    @JoinColumn(name = "schoolid", foreignKey = @ForeignKey(name = "fk_schoolid"))
    private School school;

    @ManyToOne(targetEntity = LessonRoles.class)
    @JoinColumn(name = "lessonroleid", foreignKey = @ForeignKey(name = "fk_lessonroleid"))
    private LessonRoles lessonRoles;

    @OneToMany(targetEntity = LessonSubstitutes.class)
    @JoinColumn(name = "lessonid")
    private List<LessonSubstitutes> substitutes;
}
