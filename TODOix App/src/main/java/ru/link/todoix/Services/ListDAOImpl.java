package ru.link.todoix.Services;

import ru.link.todoix.Objects.ListDTO;

import java.util.*;

public interface ListDAOImpl {

    void create(ListDTO listDTO);

    ListDTO findById(UUID id);

    void update(ListDTO listDTO);

    void deleteById(UUID id);

    List<ListDTO> getAll();

    List<ListDTO> getPage(int page, int size, String sortBy);
}
