package de.structuremade.ms.homeworkservice.util.database.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "homework", schema = "services")
@Getter
@Setter
public class Homework {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private String id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private Date validTill;

    @Column
    private String data;

    @ManyToOne(targetEntity = LessonRoles.class)
    @JoinColumn(name = "lesson", foreignKey = @ForeignKey(name = "fk_lesson"))
    private LessonRoles lesson;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "creator", foreignKey = @ForeignKey(name = "fk_creator"))
    private User creator;
}
