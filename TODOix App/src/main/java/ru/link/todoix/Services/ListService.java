package ru.link.todoix.Services;

import ru.link.todoix.Objects.ListDTO;
import ru.link.todoix.PostModels.ReviewModel;

import java.util.*;

/**
 * Интерфейс сервиса для работы с репозиторием списка дел
 */
public interface ListService {

    /**
     * Создание списка дел
     * @param listDTO - DTO списка дел
     */
    void create(ListDTO listDTO);

    /**
     * Поиск списка дел по UUID
     * @param id - UUID списка дел
     * @return ListDTO - DTO списка дел
     */
    ListDTO findById(UUID id);

    /**
     * Обновление записи списка дел
     * @param listDTO - DTO списка дел
     */
    void update(ListDTO listDTO);

    /**
     * Удаление записи о списке дел
     * @param id - UUID списка дел
     */
    void deleteById(UUID id);

    /**
     * Получение всех списков дел
     * @return List<ListDTO>
     */
    List<ListDTO> getAll();

    /**
     * Получение списков дел с сортировкой и пагинацией
     * @param p - номер страницы
     * @param size - размер страницы (количество элементов на ней)
     * @param sort - параметр, по которому будет произведена сортировка
     * @return ReviewModel
     */
    ReviewModel getPage(int p, int size, String sort);
}
