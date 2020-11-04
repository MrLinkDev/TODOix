package ru.link.todoix.Repositories;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.link.todoix.Entities.ListEntity;

import java.util.*;

/**
 * Набор методов для работы с таблицей списков дел
 */
public interface ListRepository extends JpaRepository<ListEntity, Long> {
    String findQuery = "SELECT list FROM ListEntity list WHERE list.listId = :id";
    String updateQuery = "UPDATE ListEntity list SET list.name=:name, list.modifyDate=:modify_date  WHERE list.listId = :id";
    String deleteQuery = "DELETE FROM ListEntity list WHERE list.listId = :id";

    /**
     * Поиск списка дел по его UUID
     *
     * @param id - UUID списка дел
     * @return ListEntity - сущность списка дел
     */
    @Query(findQuery)
    ListEntity findById(@Param("id") UUID id);

    /**
     * Обновление записи о списке дел по его UUID
     *
     * @param id         - UUID списка дел
     * @param name       - новое имя списка дел
     * @param modifyDate - дата изменения списка дел
     */
    @Query(updateQuery)
    @Modifying
    @Transactional
    void updateById(@Param("id") UUID id, @Param("name") String name, @Param("modify_date") Date modifyDate);

    /**
     * Удаление записи о списке дел по его UUID
     *
     * @param id - UUID списка дел
     */
    @Query(deleteQuery)
    @Modifying(clearAutomatically = true)
    @Transactional
    void deleteById(@Param("id") UUID id);
}
