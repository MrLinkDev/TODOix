package ru.link.todoix.DTO;

import lombok.Data;
import ru.link.todoix.DTO.*;

import java.util.*;

/**
 * Модель возвращаемых данных для списка дел
 */
@Data
public class ListModelDTO {
    private UUID id;

    private String name;

    private Date createDate;
    private Date modifyDate;

    private int finishedCount = 0;
    private int openedCount = 0;

    private List<TaskDTO> tasks;

    /**
     * Конструктор создания возвращаемой модели
     *
     * @param listDTO - DTO списка дел
     */
    public ListModelDTO(ListDTO listDTO){
        id = listDTO.getId();
        name = listDTO.getName();
        createDate = listDTO.getCreateDate();
        modifyDate = listDTO.getModifyDate();
    }

    /**
     * Присвоение дел, привязанных к списку дел;
     * подсчёт завершённых и открытых дел
     *
     * @param tasks - дела, привязанные к списку
     */
    public void setTasks(List<TaskDTO> tasks){
        this.tasks = tasks;

        for(TaskDTO t: tasks)
            if (t.isFinished()) ++finishedCount;
            else ++openedCount;
    }
}
