package ru.link.todoix.Entities;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.*;

/**
 * Сущность дела
 */
@Entity
@Table(name = "task", schema = "todoix_app", catalog = "todoix")
@Data
public class TaskEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_list_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private ListEntity taskListId;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Basic
    @Column(name = "finished")
    private boolean finished;

    @Basic
    @Column(name = "create_date")
    private Date createDate;

    @Basic
    @Column(name = "modify_date")
    private Date modifyDate;
}
