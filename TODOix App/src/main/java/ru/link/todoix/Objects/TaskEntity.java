package ru.link.todoix.Objects;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.*;

/**
 * Java dor на русском для класса и для полей
 */
@Entity
@Table(name = "task", schema = "todoix_app", catalog = "todoix")
@Data
@NoArgsConstructor // TODO: зачем? в Java если нет ни одного конструтора то считается что дефолтный есть
public class TaskEntity {

    @Id
    @GeneratedValue // TODO: не нужна нам тут генерация, мы UUID сами раздаём же
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
