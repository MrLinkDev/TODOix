package ru.link.todoix.DTO;

import lombok.Data;
import ru.link.todoix.DTO.ListDTO;

import java.util.List;

/**
 * Модель возвращаемых данных для списка списков дел
 */
@Data
public class ReviewModelDTO {
    private int finishedListCount = 0;
    private int openedListCount = 0;

    private List<ListDTO> lists;
}
