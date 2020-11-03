package ru.link.todoix.Objects;

import lombok.Data;

import java.util.*;

/**
 * DTO списка дел
 */
@Data
public class ListDTO {  // TODO: все DTO лучше вынеси  вотдельный пакет (каталог)

    private UUID id;

    private String name;

    private Date createDate;

    private Date modifyDate;
}
