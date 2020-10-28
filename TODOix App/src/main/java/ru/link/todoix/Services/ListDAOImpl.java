package ru.link.todoix.Services;

import ru.link.todoix.Objects.ListDTO;

import java.util.UUID;

public interface ListDAOImpl {

    public void create(ListDTO listDTO);

    public ListDTO findById(UUID id);

    public void update(ListDTO listDTO);

    public void deleteById(UUID id);
}
