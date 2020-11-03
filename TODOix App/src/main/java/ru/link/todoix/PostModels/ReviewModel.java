package ru.link.todoix.PostModels;

import lombok.Data;
import ru.link.todoix.Objects.*;

import java.util.List;

/**
 * Модель возвращаемых данных для списка списков дел
 */
@Data
public class ReviewModel { // TODO: это ДТО, поэтому лучше приписать в конце Dto,
    // чтоб по имени класса было понятно зачем он
    private int finishedListCount = 0;
    private int openedListCount = 0;

    private List<ListDTO> lists;
}
