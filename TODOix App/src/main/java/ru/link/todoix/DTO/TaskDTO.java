package ru.link.todoix.DTO;

import lombok.Data;
import ru.link.todoix.DTO.ListDTO;
import ru.link.todoix.Entities.Priority;

import java.util.*;

/**
 * DTO дела
 */
@Data
public class TaskDTO {

    private UUID id;

    private ListDTO listId;

    private String name;

    private String description;

    private Priority priority;

    private boolean finished;

    private Date createDate;

    private Date modifyDate;
}
