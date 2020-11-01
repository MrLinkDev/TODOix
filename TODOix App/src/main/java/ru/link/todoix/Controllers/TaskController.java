package ru.link.todoix.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.link.todoix.Exceptions.PageExceptions.*;
import ru.link.todoix.Exceptions.TaskExceptions.TaskNotFoundException;
import ru.link.todoix.Exceptions.TaskListExceptions.TaskListNotFoundException;
import ru.link.todoix.Objects.*;
import ru.link.todoix.Repositories.TaskRepository;
import ru.link.todoix.Services.*;

import java.util.*;

/**
 * Контроллер для запросов, связанных с делами
 */
@RestController
@RequestMapping("/todoix")
public class TaskController {
    @Autowired
    private TaskServiceImpl taskService = new TaskServiceImpl();

    @Autowired
    private ListServiceImpl listService = new ListServiceImpl();

    /**
     * Создание дела
     * @param listId - UUID списка дел, к которому будет привязано дела
     * @param name - название дела
     * @param description - описание дела
     * @param priority - срочность дела
     */
    @PostMapping(value = "/task/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTask(@RequestParam UUID listId, @RequestParam String name, @RequestParam(required = false) String description, @RequestParam String priority)
            throws TaskListNotFoundException {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(UUID.randomUUID());

        try {
            taskDTO.setListId(listService.findById(listId));
        } catch (NullPointerException e){
            throw new TaskListNotFoundException();
        }

        taskDTO.setName(name);
        taskDTO.setDescription(description);
        taskDTO.setPriority(Priority.valueOfString(priority));
        taskDTO.setFinished(false);
        taskDTO.setCreateDate(new Date(System.currentTimeMillis()));
        taskDTO.setModifyDate(new Date(System.currentTimeMillis()));

        taskService.create(taskDTO);
    }

    /**
     * Получение дела по его UUID
     * @param id - UUID дела
     * @return сущность дела
     */
    @GetMapping(value = "/task/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDTO getTask(@PathVariable("taskId") final UUID id) throws TaskNotFoundException {
        try {
            return taskService.findById(id);
        } catch (NullPointerException e) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Изменение записи о деле по его UUID
     * @param id - UUID дела
     * @param name - новое название дела
     * @param description - новое описание дела
     * @param priority - новая срочность дела
     * @param finished - новый статус дела
     */
    @PutMapping(value = "/task/{taskId}/modify")
    @ResponseStatus(HttpStatus.OK)
    public void modifyTask(@PathVariable("taskId") final UUID id, @RequestParam(required = false) String name, @RequestParam(required = false) String description, @RequestParam(required = false) String priority, @RequestParam(required = false) Boolean finished)
            throws TaskNotFoundException {
        TaskDTO taskDTO;
        try {
            taskDTO = taskService.findById(id);
        } catch (NullPointerException e){
            throw new TaskNotFoundException();
        }

        if (name != null) taskDTO.setName(name);
        if (description != null) taskDTO.setDescription(description);
        if (priority != null) taskDTO.setPriority(Priority.valueOfString(priority));
        if (finished != null) taskDTO.setFinished(finished);
        taskDTO.setModifyDate(new Date(System.currentTimeMillis()));

        taskService.update(taskDTO);
    }

    /**
     * Присвоение делу статуса завершённого
     * @param id - UUID дела
     */
    @PutMapping(value = "/task/{taskId}/markDown")
    @ResponseStatus(HttpStatus.OK)
    public void markDownTask(@PathVariable("taskId") final UUID id) throws TaskNotFoundException {
        TaskDTO taskDTO;
        try {
            taskDTO = taskService.findById(id);
        } catch (NullPointerException e){
            throw new TaskNotFoundException();
        }
        taskDTO.setFinished(true);

        taskService.update(taskDTO);
    }

    /**
     * Удаление дела по его UUID
     * @param id - UUID дела
     */
    @DeleteMapping(value = "/task/{taskId}/delete")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteTask(@PathVariable("taskId") final UUID id){
        taskService.deleteById(id);
    }

    /**
     * Получение всех дел, нужен был для отладки
     * @return List<TaskDTO>
     */
    @GetMapping(value = "/task/all")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDTO> getAll(){
        return taskService.getAll();
    }

    /**
     * Получение списка дел
     * @param p - страница, которую нужно получить
     * @param size - размер страницы (количество элементов на ней)
     * @param sortBy - параметр сортировки (id, name, listId, createDate, modifyDate, description, priority)
     * @return List<TaskDTO> - список дел
     */
    @GetMapping(value = "/task/review")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDTO> getReview(@RequestParam(defaultValue = "0") Integer p, @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "name") String sortBy)
            throws PageIndexException, PageSizeException, PageSortException {
        if (p < 0) throw new PageIndexException();
        if (size < 1) throw new PageSizeException();
        else if (size > 100) size = 10;

        try{
            return taskService.getPage(p, size, sortBy);
        } catch (PropertyReferenceException e){
            throw new PageSortException();
        }
    }

}
