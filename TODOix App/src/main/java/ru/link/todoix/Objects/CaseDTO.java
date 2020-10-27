package ru.link.todoix.Objects;

import lombok.Data;

import java.util.UUID;

@Data
public class CaseDTO {
    private UUID caseId;
    private UUID listId;
    private String name;
    private String description;
    private short priority;
}
