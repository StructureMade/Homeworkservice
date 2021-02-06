package de.structuremade.ms.homeworkservice.util.database.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "homeworkdone")
@Getter
@Setter
public class HomeworkDone {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private String id;

    @ManyToOne(targetEntity = Homework.class)
    @JoinColumn(name = "homework", foreignKey = @ForeignKey(name = "fk_homework"))
    private Homework homework;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userid", foreignKey = @ForeignKey(name = "fk_userid"))
    private User user;

    @Column
    private String data;
}
