package ru.link.todoix.Case;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseRepository extends JpaRepository<CaseEntity, Long> {
    public CaseEntity findById(long id);
}
