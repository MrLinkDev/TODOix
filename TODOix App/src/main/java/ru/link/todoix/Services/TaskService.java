package ru.link.todoix.Services;

import ru.link.todoix.Objects.*;

import java.util.*;

public interface TaskService {

    void create(TaskDTO taskDTO);

    TaskDTO findById(UUID id);

    void update(TaskDTO taskDTO);

    void deleteById(UUID id);

    List<TaskDTO> getAll();

    List<TaskDTO> getPage(int p, int size, String sort);

    List<TaskDTO> findByList(ListDTO listDTO);

}
