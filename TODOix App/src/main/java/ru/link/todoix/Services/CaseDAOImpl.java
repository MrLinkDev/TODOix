package ru.link.todoix.Services;

import ru.link.todoix.Objects.*;

import java.util.*;

public interface CaseDAOImpl {

    void create(CaseDTO caseDTO);

    CaseDTO findById(UUID id);

    void update(CaseDTO caseDTO);

    void deleteById(UUID id);

    List<CaseDTO> getAll();

    List<CaseDTO> getPage(int page, int size, String sortBy);

}
