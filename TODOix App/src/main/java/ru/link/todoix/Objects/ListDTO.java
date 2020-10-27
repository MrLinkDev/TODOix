package ru.link.todoix.Objects;

import lombok.Data;

import java.util.*;

@Data
public class ListDTO {
    private UUID id;
    private String name;
    private Date createDate;
    private Date modifyDate;
}
