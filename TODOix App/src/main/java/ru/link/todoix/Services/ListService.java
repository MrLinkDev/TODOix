package ru.link.todoix.Services;

import ru.link.todoix.DTO.*;
import ru.link.todoix.Exceptions.PageExceptions.*;
import ru.link.todoix.Exceptions.TaskListExceptions.*;

import java.util.*;

/**
 * Интерфейс сервиса для работы с репозиторием списка дел
 */
public interface ListService {

    /**
     * Создание списка дел
     *
     * @param listName - имя списка дел
     */
    void create(String listName);

    /**
     * Поиск списка дел по UUID
     *
     * @param id - UUID списка дел
     * @return ListModelDTO - DTO списка дел, в котором находятся дела, привязанные к данному списку дел
     */
    ListModelDTO getList(UUID id) throws TaskListNotFoundException;

    /**
     * Обновление записи списка дел
     *
     * @param id   - UUID списка дел
     * @param name - новое имя списка дел
     */
    void update(UUID id, String name) throws TaskListNotFoundException, TaskListEmptyNameException;

    /**
     * Удаление записи о списке дел и всех привязанных к нему дел
     *
     * @param id - UUID списка дел
     */
    void deleteById(UUID id) throws TaskListNotFoundException;

    /**
     * Получение всех списков дел
     *
     * @return List<ListDTO>
     */
    List<ListDTO> getAll();

    /**
     * Получение списков дел с сортировкой и пагинацией
     *
     * @param p    - номер страницы
     * @param size - размер страницы (количество элементов на ней)
     * @param sort - параметр, по которому будет произведена сортировка
     * @return ReviewModel
     */
    ReviewModelDTO getPage(int p, int size, String sort) throws PageIndexException, PageSizeException, PageSortException;
}
