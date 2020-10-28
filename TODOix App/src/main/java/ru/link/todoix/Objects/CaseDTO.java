package ru.link.todoix.Objects;

import lombok.Data;

import java.util.*;

@Data
public class CaseDTO {
    private UUID caseId;
    private ListDTO listId;
    private String name;
    private String description;
    private short priority;
    private boolean finished;
    private Date createDate;
    private Date modifyDate;
}
