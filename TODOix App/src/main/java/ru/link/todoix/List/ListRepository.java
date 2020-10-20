package ru.link.todoix.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository<ListEntity, Long> {

    public ListEntity findById(long id);
}
