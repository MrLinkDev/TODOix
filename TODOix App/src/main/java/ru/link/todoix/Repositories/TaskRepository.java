package ru.link.todoix.Repositories;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.link.todoix.Objects.*;

import java.util.*;

/**
 * Набор методов для работы с таблицей заданий
 */
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    String findByIdQuery = "SELECT taskEntity FROM TaskEntity taskEntity WHERE taskEntity.id = :id";
    String findByListQuery = "SELECT taskEntity FROM TaskEntity taskEntity WHERE taskEntity.taskListId = :list";
    String updateQuery = "UPDATE TaskEntity taskEntity SET " +
            "taskEntity.name=:name, " +
            "taskEntity.description=:description, " +
            "taskEntity.priority=:priority, " +
            "taskEntity.finished=:finished, " +
            "taskEntity.modifyDate=:modify_date " +
            "WHERE taskEntity.id = :id";
    String markDownQuery = "UPDATE TaskEntity taskEntity SET " +
            "taskEntity.finished=true, " +
            "taskEntity.modifyDate=:modify_date " +
            "WHERE taskEntity.id = :id";
    String deleteQuery = "DELETE FROM TaskEntity taskEntity WHERE taskEntity.id = :id";

    /**
     * Поиск дела по его UUID
     * @param id - UUID дела
     * @return TaskEntity - сущность дела
     */
    @Query(findByIdQuery)
    TaskEntity findById(@Param("id") UUID id);

    /**
     * Поиск дел по UUID списка дел, к которому привязаны дела
     * @param list - список дел
     * @return List<TaskEntity>
     */
    @Query(findByListQuery)
    List<TaskEntity> findByList(@Param("list") ListEntity list);

    /**
     * Обновление записи о деле по его UUID
     * @param id - UUID дела
     * @param name - имя дела
     * @param description - краткое описание дела
     * @param priority - срочность дела (1-5)
     * @param finished - статус дела (завершено или нет)
     * @param modifyDate - дата изменения дела
     */
    @Query(updateQuery)
    @Modifying
    @Transactional
    void updateById(
            @Param("id") UUID id,
            @Param("name") String name,
            @Param("description") String description,
            @Param("priority") Priority priority,
            @Param("finished") boolean finished,
            @Param("modify_date") Date modifyDate);

    /**
     * Присвоение делу статуса завершённого
     * @param id - UUID дела
     * @param modifyDate - дата изменения записи о деле
     */
    @Query(markDownQuery)
    @Modifying
    @Transactional
    void markDownById(
            @Param("id") UUID id,
            @Param("modify_date") Date modifyDate);

    /**
     * Удаление дела по его UUID
     * @param id - UUID дела
     */
    @Query(deleteQuery)
    @Modifying(clearAutomatically = true)
    @Transactional
    void deleteById(@Param("id") UUID id);
}
