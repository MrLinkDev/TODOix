package ru.link.todoix.Case;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaseRepository extends JpaRepository<CaseItem, Long> {

}
