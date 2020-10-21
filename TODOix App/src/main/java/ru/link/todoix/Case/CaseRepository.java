package ru.link.todoix.Case;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.UUID;

public interface CaseRepository extends JpaRepository<CaseEntity, Long> {
    String findQuery = "SELECT caseEntity FROM CaseEntity caseEntity WHERE caseEntity.caseId = :id";
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

    @Query(findQuery)
    CaseEntity findById(@Param("id") UUID id);

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

    @Query(markDownQuery)
    @Modifying
    @Transactional
    void markDownById(
            @Param("id") UUID id,
            @Param("modify_date") Timestamp modifyDate);

    @Query(deleteQuery)
    @Modifying(clearAutomatically = true)
    @Transactional
    void deleteById(@Param("id") UUID id);
}
