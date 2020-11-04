package ru.link.todoix.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;
import ru.link.todoix.DTO.*;
import ru.link.todoix.Entities.*;
import ru.link.todoix.Exceptions.PageExceptions.*;
import ru.link.todoix.Exceptions.TaskListExceptions.*;
import ru.link.todoix.Repositories.*;

import java.util.*;

/**
 * Сервис для работы с репозиторием списка дел
 */
@Service
public class ListServiceImpl implements ListService {
    @Autowired
    private ListRepository listRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void create(String listName) {
        ListEntity listEntity = new ListEntity();
        listEntity.setName(listName);
        listEntity.setListId(UUID.randomUUID());
        listEntity.setCreateDate(new Date(System.currentTimeMillis()));
        listEntity.setModifyDate(new Date(System.currentTimeMillis()));
        listRepository.save(listEntity);
    }

    @Override
    public ListModelDTO getList(UUID id) throws TaskListNotFoundException {
        ListModelDTO listModel;
        try {
            listModel = new ListModelDTO(Converter.entityToDTO(listRepository.findById(id)));
        } catch (NullPointerException e){
            throw new TaskListNotFoundException();
        }

        List<TaskEntity> tasks = taskRepository.findByList(listRepository.findById(id));
        listModel.setTasks(Converter.convertListOfTaskEntitiesToDTO(tasks));

        return listModel;
    }

    @Override
    public void update(UUID id, String name) throws TaskListNotFoundException, TaskListEmptyNameException {
        ListEntity listEntity = listRepository.findById(id);

        try {
            if (name.isEmpty()) {
                throw new TaskListEmptyNameException();
            }

            listEntity.setName(name);
            listEntity.setModifyDate(new Date(System.currentTimeMillis()));
        } catch (NullPointerException e){
            throw new TaskListNotFoundException();
        }
    }

    @Override
    public void deleteById(UUID id) throws TaskListNotFoundException{
        if (listRepository.findById(id) == null) {
            throw new TaskListNotFoundException();
        }

        List<TaskEntity> tasks = taskRepository.findByList(listRepository.findById(id));
        for (TaskEntity task : tasks){
            taskRepository.deleteById(task.getId());
        }

        listRepository.deleteById(id);
    }

    @Override
    public List<ListDTO> getAll() {
        List<ListEntity> out = listRepository.findAll();
        return Converter.convertListOfListEntitiesToDTO(out);
    }

    @Override
    public ReviewModelDTO getPage(int p, int size, String sort) throws PageIndexException, PageSizeException, PageSortException {
        if (p < 0) {
            throw new PageIndexException();
        }
        if (size < 1) {
            throw new PageSizeException();
        }
        if (size > 100) {
            size = 10;
        }

        Pageable pageable = PageRequest.of(p, size, Sort.by(sort));
        Page<ListEntity> page;

        try{
            page = listRepository.findAll(pageable);
        } catch (PropertyReferenceException e){
            throw new PageSortException();
        }


        int finishedListCount = 0;
        int openedListCount = 0;

        for (ListEntity list : page.getContent()){
            boolean finished = true;
            List<TaskEntity> tasks = taskRepository.findByList(list);
            for (TaskEntity taskItem : tasks){
                if (!taskItem.isFinished()) {
                    finished = false;
                    break;
                }
            }

            if (finished) {
                ++finishedListCount;
            } else {
                ++openedListCount;
            }
        }

        ReviewModelDTO review = new ReviewModelDTO();
        review.setLists(Converter.convertListOfListEntitiesToDTO(page.getContent()));
        review.setOpenedListCount(openedListCount);
        review.setFinishedListCount(finishedListCount);

        return review;
    }
}
