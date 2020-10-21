package ru.link.todoix.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.UUID;

public interface ListRepository extends JpaRepository<ListEntity, Long> {
    String findQuery = "SELECT list FROM ListEntity list WHERE list.listId = :id";
    String updateQuery = "UPDATE ListEntity list SET list.name=:name, list.modifyDate=:modify_date  WHERE list.listId = :id";
    String deleteQuery = "DELETE FROM ListEntity list WHERE list.listId = :id";

    @Query(findQuery)
    ListEntity findById(@Param("id") UUID id);

    @Query(updateQuery)
    @Modifying
    @Transactional
    void updateById(@Param("id") UUID id, @Param("name") String name, @Param("modify_date") Timestamp modifyDate);

    @Query(deleteQuery)
    @Modifying(clearAutomatically = true)
    @Transactional
    void deleteById(@Param("id") UUID id);
}
