package ru.link.todoix.DTO;

import lombok.Data;

import java.util.*;

/**
 * DTO списка дел
 */
@Data
public class ListDTO {

    private UUID id;

    private String name;

    private Date createDate;

    private Date modifyDate;
}
