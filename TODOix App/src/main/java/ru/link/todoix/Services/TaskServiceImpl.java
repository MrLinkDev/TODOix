package ru.link.todoix.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;
import ru.link.todoix.*;
import ru.link.todoix.DTO.*;
import ru.link.todoix.Entities.*;
import ru.link.todoix.Enums.Priority;
import ru.link.todoix.Exceptions.PageExceptions.*;
import ru.link.todoix.Exceptions.TaskExceptions.TaskNotFoundException;
import ru.link.todoix.Exceptions.TaskListExceptions.TaskListNotFoundException;
import ru.link.todoix.Repositories.*;

import java.util.*;

/**
 * Сервис для работы с репозиторием дел
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ListRepository listRepository;

    @Override
    public void create(UUID listId, String name, String description, Priority priority) throws TaskListNotFoundException {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(UUID.randomUUID());

        try {
            taskEntity.setTaskListId(listRepository.findById(listId));
        } catch (NullPointerException e){
            throw new TaskListNotFoundException();
        }

        taskEntity.setName(name);
        taskEntity.setDescription(description);
        taskEntity.setPriority(priority);
        taskEntity.setFinished(false);
        taskEntity.setCreateDate(new Date(System.currentTimeMillis()));
        taskEntity.setModifyDate(new Date(System.currentTimeMillis()));
    }

    @Override
    public TaskDTO findById(UUID id) throws TaskNotFoundException {
        TaskEntity taskEntity = taskRepository.findById(id);
        if (taskEntity == null) {
            throw new TaskNotFoundException();
        }

        return Converter.entityToDTO(taskEntity);
    }

    @Override
    public void update(UUID id, String name, String description, Priority priority, Boolean finished) throws TaskNotFoundException {
        TaskEntity taskEntity = taskRepository.findById(id);
        if (taskEntity == null) {
            throw new TaskNotFoundException();
        }

        taskRepository.updateById(
                id,
                name == null ? taskEntity.getName() : name,
                description == null ? taskEntity.getDescription() : description,
                priority == null ? taskEntity.getPriority() : priority,
                finished == null ? taskEntity.isFinished() : finished,
                new Date(System.currentTimeMillis())
        );
    }

    @Override
    public void markDown(UUID id) throws TaskNotFoundException {
        if (taskRepository.findById(id) == null) {
            throw new TaskNotFoundException();
        }

        taskRepository.markDownById(id, new Date(System.currentTimeMillis()));
    }

    @Override
    public void deleteById(UUID id) throws TaskNotFoundException {
        if (taskRepository.findById(id) == null) {
            throw new TaskNotFoundException();
        }

        taskRepository.deleteById(id);
    }

    @Override
    public List<TaskDTO> getAll() {
        List<TaskEntity> out = taskRepository.findAll();
        return Converter.convertListOfTaskEntitiesToDTO(out);
    }

    @Override
    public List<TaskDTO> getPage(int p, int size, String sort) throws PageIndexException, PageSizeException, PageSortException {
        if (p < 0) {
            throw new PageIndexException();
        }
        if (size < 1) {
            throw new PageSizeException();
        }
        if (size > 100) {
            size = 10;
        }

        Pageable pageable = PageRequest.of(p, size, Sort.by(sort));
        Page<TaskEntity> page;

        try {
            page = taskRepository.findAll(pageable);
        } catch (PropertyReferenceException e) {
            throw new PageSortException();
        }

        return Converter.convertListOfTaskEntitiesToDTO(page.getContent());
    }
}
