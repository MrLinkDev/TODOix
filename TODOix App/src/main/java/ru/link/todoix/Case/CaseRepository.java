package ru.link.todoix.Case;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

/**
 * Набор методов для работы с таблицей дел
 */
public interface CaseRepository extends JpaRepository<CaseEntity, Long> {
    String findByIdQuery = "SELECT caseEntity FROM CaseEntity caseEntity WHERE caseEntity.caseId = :id";
    String findByListIdQuery = "SELECT caseEntity FROM CaseEntity caseEntity WHERE caseEntity.listId = :id";
    String updateQuery = "UPDATE CaseEntity caseEntity SET " +
            "caseEntity.name=:name, " +
            "caseEntity.description=:description, " +
            "caseEntity.urgency=:urgency, " +
            "caseEntity.finished=:finished, " +
            "caseEntity.modifyDate=:modify_date " +
            "WHERE caseEntity.caseId = :id";
    String markDownQuery = "UPDATE CaseEntity caseEntity SET " +
            "caseEntity.finished=true, " +
            "caseEntity.modifyDate=:modify_date " +
            "WHERE caseEntity.caseId = :id";
    String deleteQuery = "DELETE FROM CaseEntity caseEntity WHERE caseEntity.caseId = :id";

    /**
     * Поиск дела по его UUID
     * @param id - UUID дела
     * @return CaseEntity - сущность дела
     */
    @Query(findByIdQuery)
    CaseEntity findById(@Param("id") UUID id);

    /**
     * Поиск дел по UUID списка дел, к которому привязаны дела
     * @param id - UUID списка дел
     * @return List<CaseEntity>
     */
    @Query(findByListIdQuery)
    List<CaseEntity> findByListId(@Param("id") UUID id);

    /**
     * Обновление записи о деле по его UUID
     * @param id - UUID дела
     * @param name - имя дела
     * @param description - краткое описание дела
     * @param urgency - срочность дела (1-5)
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
            @Param("urgency") short urgency,
            @Param("finished") boolean finished,
            @Param("modify_date") Timestamp modifyDate);

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
            @Param("modify_date") Timestamp modifyDate);

    /**
     * Удаление дела по его UUID
     * @param id - UUID дела
     */
    @Query(deleteQuery)
    @Modifying(clearAutomatically = true)
    @Transactional
    void deleteById(@Param("id") UUID id);
}
