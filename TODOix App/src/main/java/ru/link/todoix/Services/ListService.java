package ru.link.todoix.Services;

import ru.link.todoix.Objects.ListDTO;
import ru.link.todoix.PostModels.ReviewPostModel;

import java.util.*;

public interface ListService {

    void create(ListDTO listDTO);

    ListDTO findById(UUID id);

    void update(ListDTO listDTO);

    void deleteById(UUID id);

    List<ListDTO> getAll();

    ReviewPostModel getPage(int p, int size, String sort);
}
