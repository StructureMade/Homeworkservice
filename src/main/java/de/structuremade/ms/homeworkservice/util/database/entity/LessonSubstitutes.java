package de.structuremade.ms.homeworkservice.util.database.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lessonsubstitutes", schema = "services", indexes = {
        @Index(name = "id_lessonrolesid", columnList = "id", unique = true)})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonSubstitutes {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private String id;

    @Column
    private String substituteRoom;

    @Column(name = "dateofsubstitute")
    private Date DateOfSubstitute;

    @ManyToOne(targetEntity =User.class)
    @JoinColumn(name = "substituteTeacher", foreignKey = @ForeignKey(name = "fk_substituteTeacher"))
    private User substituteTeacher;

    @ManyToOne(targetEntity = Lessons.class)
    @JoinColumn(name = "lesson")
    private Lessons lesson;

    @ManyToOne
    @JoinColumn(name = "editor", foreignKey = @ForeignKey(name = "fk_user"))
    private User user;
}
