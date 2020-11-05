package ru.link.todoix.Entities;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.*;

/**
 * Сущность списка дел
 */
@Entity
@Table(name = "task_list", schema = "todoix_app", catalog = "todoix")
@Data
public class ListEntity {

    @Id
    @Column(name = "id")
    private UUID listId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private UserEntity userId;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "create_date")
    private Date createDate;

    @Basic
    @Column(name = "modify_date")
    private Date modifyDate;


}
