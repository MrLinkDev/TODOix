package ru.link.todoix.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.link.todoix.DTO.TaskDTO;
import ru.link.todoix.Exceptions.PageExceptions.*;
import ru.link.todoix.Exceptions.TaskExceptions.TaskNotFoundException;
import ru.link.todoix.Exceptions.TaskListExceptions.TaskListNotFoundException;
import ru.link.todoix.Entities.*;
import ru.link.todoix.Services.*;

import java.util.*;

/**
 * Контроллер для запросов, связанных с делами
 */
@RestController
@RequestMapping("/todoix")
public class TaskController {

    @Autowired
    private TaskServiceImpl taskService;

    /**
     * Создание дела
     *
     * @param listId      - UUID списка дел, к которому будет привязано дела
     * @param name        - название дела
     * @param description - описание дела
     * @param priority    - срочность дела
     */
    @PostMapping(value = "/task/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTask(@RequestParam UUID listId, @RequestParam String name, @RequestParam(required = false) String description,
                           @RequestParam Priority priority
    ) throws TaskListNotFoundException {
        taskService.create(listId, name, description, priority);
    }

    /**
     * Получение дела по его UUID
     *
     * @param id - UUID дела
     * @return сущность дела
     */
    @GetMapping(value = "/task/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDTO getTask(@PathVariable("taskId") final UUID id) throws TaskNotFoundException {
        return taskService.findById(id);
    }

    /**
     * Изменение записи о деле по его UUID
     *
     * @param id          - UUID дела
     * @param name        - новое название дела
     * @param description - новое описание дела
     * @param priority    - новая срочность дела
     * @param finished    - новый статус дела
     */
    @PutMapping(value = "/task/{taskId}/modify")
    @ResponseStatus(HttpStatus.OK)
    public void modifyTask(@PathVariable("taskId") final UUID id, @RequestParam(required = false) String name, @RequestParam(required = false) String description,
                           @RequestParam(required = false) Priority priority, @RequestParam(required = false) Boolean finished
    ) throws TaskNotFoundException {
        taskService.update(id, name, description, priority, finished);
    }

    /**
     * Присвоение делу статуса завершённого
     *
     * @param id - UUID дела
     */
    @PutMapping(value = "/task/{taskId}/markDown")
    @ResponseStatus(HttpStatus.OK)
    public void markDownTask(@PathVariable("taskId") final UUID id) throws TaskNotFoundException {
        taskService.markDown(id);
    }

    /**
     * Удаление дела по его UUID
     *
     * @param id - UUID дела
     */
    @DeleteMapping(value = "/task/{taskId}/delete")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteTask(@PathVariable("taskId") final UUID id) throws TaskNotFoundException{
        taskService.deleteById(id);
    }

    /**
     * Получение всех дел, нужен был для отладки
     *
     * @return List<TaskDTO>
     */
    @GetMapping(value = "/task/all")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDTO> getAll() {
        return taskService.getAll();
    }

    /**
     * Получение списка дел
     *
     * @param p      - страница, которую нужно получить
     * @param size   - размер страницы (количество элементов на ней)
     * @param sortBy - параметр сортировки (id, name, listId, createDate, modifyDate, description, priority)
     * @return List<TaskDTO> - список дел
     */
    @GetMapping(value = "/task/review")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDTO> getReview(@RequestParam(defaultValue = "0") Integer p, @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "name") String sortBy)
            throws PageIndexException, PageSizeException, PageSortException {
        return taskService.getPage(p, size, sortBy);
    }
}
