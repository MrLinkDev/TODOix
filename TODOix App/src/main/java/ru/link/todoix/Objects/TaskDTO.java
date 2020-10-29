package ru.link.todoix.Objects;

import lombok.Data;

import java.util.*;

@Data
public class TaskDTO {
    private UUID Id;
    private ListDTO listId;
    private String name;
    private String description;
    private Priority priority;
    private boolean finished;
    private Date createDate;
    private Date modifyDate;
}
