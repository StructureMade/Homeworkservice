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

    @ManyToOne
    @JoinColumn(name = "school", foreignKey = @ForeignKey(name = "fk_school"))
    private School school;

    @ManyToOne
    @JoinColumn(name = "lessonrole", foreignKey = @ForeignKey(name = "fk_lessonrole"))
    private LessonRoles lessonRole;

    @OneToMany(targetEntity = LessonSubstitutes.class)
    @JoinColumn(name = "lesson")
    private List<LessonSubstitutes> lesson;

    @OneToMany(targetEntity = Homework.class)
    @JoinColumn(name = "lesson", foreignKey = @ForeignKey(name = "fk_lessonrole"))
    private List<Homework> homework;

    @OneToMany(targetEntity = LessonSubstitutes.class)
    @JoinColumn(name = "lesson")
    private List<LessonSubstitutes> substitutes;


}
