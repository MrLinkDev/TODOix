package ru.link.todoix.Objects;

import lombok.*;
import ru.link.todoix.Objects.ListEntity;

import javax.persistence.*;
import java.util.*;
import java.util.Date;

/**
 * Сущность дела
 */
@Entity @Table(name = "cases", schema = "todoix_app", catalog = "todoix")
@Data @RequiredArgsConstructor(access = AccessLevel.PUBLIC) @NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "_id")
    private UUID caseId;

    @ManyToOne
    @JoinColumn(name = "list_id")
    @NonNull private ListEntity listId;

    @Basic
    @Column(name = "name")
    @NonNull private String name;

    @Basic
    @Column(name = "description")
    @NonNull private String description;

    @Basic
    @Column(name = "priority")
    @NonNull private short priority;

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
