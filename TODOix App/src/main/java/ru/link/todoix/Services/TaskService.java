package ru.link.todoix.Services;

import ru.link.todoix.Objects.*;

import java.util.*;

/**
 * Интерфейс сервиса для работы с репозиторием дел
 */
public interface TaskService {

    /**
     * Создание дела
     * @param taskDTO - DTO дела
     */
    void create(TaskDTO taskDTO);

    /**
     * Поиск дела по его UUID
     * @param id - UUID дела
     * @return TaskDTO
     */
    TaskDTO findById(UUID id);

    /**
     * Обновление записи о деле
     * @param taskDTO - DTO дела
     */
    void update(TaskDTO taskDTO);

    /**
     * Удаление дела по его UUID
     * @param id - UUID дела
     */
    void deleteById(UUID id);

    /**
     * Получение списка всех дел
     * @return List<TaskDTO>
     */
    List<TaskDTO> getAll();

    /**
     * Получение списка дел с пагинацией и сортировкой
     * @param p - страница
     * @param size - размер страницы (количество элементов на ней)
     * @param sort - параметр, по которому будет производиться сортировка
     * @return List<TaskDTO>
     */
    List<TaskDTO> getPage(int p, int size, String sort);

    /**
     * Поиск всех дел, привязанных к списку
     * @param listDTO - список, по которому производится поиск
     * @return List<TaskDTO>
     */
    List<TaskDTO> findByList(ListDTO listDTO);

}
