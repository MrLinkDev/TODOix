package ru.link.todoix.Objects;

import lombok.*;

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
    public UUID listId;

    @Basic @Column(name = "name")
    @NonNull public String name;

    @Basic @Column(name = "create_date")
    public Date createDate;

    @Basic @Column(name = "modify_date")
    public Date modifyDate;


}
