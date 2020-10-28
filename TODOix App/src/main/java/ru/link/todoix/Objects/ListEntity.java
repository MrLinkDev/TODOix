package ru.link.todoix.Objects;

import lombok.*;
import org.hibernate.annotations.Filter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.*;

/**
 * Сущность списка дел
 */
@Entity @Table(name = "lists", schema = "todoix_app", catalog = "todoix")
@Data @RequiredArgsConstructor @NoArgsConstructor
public class ListEntity {

    @Id @GeneratedValue @Column(name = "_id")
    private UUID listId;

    @Basic @Column(name = "name")
    @NonNull private String name;

    @Basic @Column(name = "create_date")
    private Date createDate;

    @Basic @Column(name = "modify_date")
    private Date modifyDate;


}