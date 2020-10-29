package ru.link.todoix.PostModels;

import lombok.Data;
import ru.link.todoix.Objects.*;

import java.util.List;

@Data
public class ReviewModel {
    private int finishedListCount = 0;
    private int openedListCount = 0;

    private List<ListDTO> lists;
}
