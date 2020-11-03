package ru.link.todoix.Objects;

import lombok.Data;

import java.util.*;

/**
 * DTO дела
 */
@Data
public class TaskDTO { // TODO: все DTO лучше вынеси  вотдельный пакет (каталог)

    private UUID id;

    private ListDTO listId;

    private String name;

    private String description;

    private Priority priority;

    private boolean finished;

    private Date createDate;

    private Date modifyDate;
}
