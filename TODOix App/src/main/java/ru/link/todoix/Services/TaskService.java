package ru.link.todoix.Services;

import ru.link.todoix.DTO.*;
import ru.link.todoix.Entities.Priority;
import ru.link.todoix.Exceptions.PageExceptions.*;
import ru.link.todoix.Exceptions.TaskExceptions.TaskNotFoundException;
import ru.link.todoix.Exceptions.TaskListExceptions.TaskListNotFoundException;

import java.util.*;

/**
 * Интерфейс сервиса для работы с репозиторием дел
 */
public interface TaskService {

    /**
     * Создание дела
     *
     * @param listId      - UUID списка дел, к которому будет привязано дело
     * @param name        - имя дела
     * @param description - описание дела
     * @param priority    - срочность дела
     */
    void create(UUID listId, String name, String description, Priority priority) throws TaskListNotFoundException;

    /**
     * Поиск дела по его UUID
     *
     * @param id - UUID дела
     * @return TaskDTO
     */
    TaskDTO findById(UUID id) throws TaskNotFoundException;

    /**
     * Обновление записи о деле
     *
     *  @param id          - UUID изменяемого дела
     *  @param name        - новое имя дела
     *  @param description - новое описание дела
     *  @param priority    - новая срочность дела
     *  @param finished    - новый статус дела
     */
    void update(UUID id, String name, String description, Priority priority, Boolean finished) throws TaskNotFoundException;

    void markDown(UUID id) throws TaskNotFoundException;

    /**
     * Удаление дела по его UUID
     *
     * @param id - UUID дела
     */
    void deleteById(UUID id) throws TaskNotFoundException;

    /**
     * Получение списка всех дел
     *
     * @return List<TaskDTO>
     */
    List<TaskDTO> getAll();

    /**
     * Получение списка дел с пагинацией и сортировкой
     *
     * @param p    - страница
     * @param size - размер страницы (количество элементов на ней)
     * @param sort - параметр, по которому будет производиться сортировка
     * @return List<TaskDTO>
     */
    List<TaskDTO> getPage(int p, int size, String sort) throws PageIndexException, PageSizeException, PageSortException;
}
