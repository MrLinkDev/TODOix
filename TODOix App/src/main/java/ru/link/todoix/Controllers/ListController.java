package ru.link.todoix.Controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.link.todoix.Exceptions.PageExceptions.*;
import ru.link.todoix.Exceptions.TaskListExceptions.*;
import ru.link.todoix.PostModels.*;
import ru.link.todoix.Objects.*;
import ru.link.todoix.Services.*;

import java.util.*;

/**
 * Контроллер для запросов, связанных со списками дел
 */
@RestController
@RequestMapping("/todoix")
public class ListController {
    @Autowired
    private final ListServiceImpl listService = new ListServiceImpl();

    @Autowired
    private final TaskServiceImpl taskService = new TaskServiceImpl();

    /**
     * Создание списка дел
     * @param name - имя создаваемого списка
     */
    @PostMapping(value = "/list/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createList(@RequestParam String name) throws TaskListEmptyNameException {
        //if (name.isEmpty()) throw new TaskListEmptyNameException();

        ListDTO listDTO = new ListDTO();
        listDTO.setId(UUID.randomUUID());
        listDTO.setName(name);
        listDTO.setCreateDate(new Date(System.currentTimeMillis()));
        listDTO.setModifyDate(new Date(System.currentTimeMillis()));

        listService.create(listDTO);
    }

    /**
     * Получение списка дел по UUID
     * @param id - UUID списка дел
     * @return ListModel, в котором находится список дел
     * и сами дела, привязанные к возвращаемому списку
     */
    @GetMapping(value = "/list/{listId}")
    @ResponseStatus(HttpStatus.OK)
    public ListModel getList(@PathVariable("listId") final UUID id) throws TaskListNotFoundException {
        ListModel out;
        try {
            out = new ListModel(listService.findById(id));
        } catch (NullPointerException e){
            throw new TaskListNotFoundException();
        }
        out.setTasks(taskService.findByList(listService.findById(id)));

        return out;
    }

    /**
     * Изменение списка дел
     * @param id - UUID списка дел
     * @param name - новое имя списка дел
     */
    @PutMapping(value = "/list/{listId}/modify")
    @ResponseStatus(HttpStatus.OK)
    public void modifyList(@PathVariable("listId") final UUID id, @RequestParam String name) throws TaskListEmptyNameException, TaskListNotFoundException{
        ListDTO listDTO = listService.findById(id);

        try {
            if (name.isEmpty()) throw new TaskListEmptyNameException();

            listDTO.setName(name);
            listDTO.setModifyDate(new Date(System.currentTimeMillis()));
        } catch (NullPointerException e){
            throw new TaskListNotFoundException();
        }

        listService.update(listDTO);
    }

    /**
     * Удаление списка дел
     * @param id - UUID списка дел
     */
    @DeleteMapping(value = "/list/{listId}/delete")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteList(@PathVariable("listId") final UUID id){
        listService.deleteById(id);
    }

    /**
     * Просмотр всех списков дел, нужен был для отладки
     * @return List<ListDTO> - список всех списков, но без дел, привязанных к ним
     */
    @GetMapping(value = "/list/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ListDTO> getAll(){
        return listService.getAll();
    }

    /**
     * Получение списка списков дел
     * @param p - страница, которую нужно получить
     * @param size - размер страницы (количество элементов на ней)
     * @param sortBy - параметр сортировки (name, listId, createDate, modifyDate)
     * @return ReviewPostModel - список списков дел с количеством завершённых и открытых списков дел
     */
    @GetMapping(value = "/review")
    @ResponseStatus(HttpStatus.OK)
    public ReviewModel getReview(@RequestParam(defaultValue = "0") Integer p, @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "name") String sortBy)
            throws PageIndexException, PageSizeException, PageSortException {
        if (p < 0) throw new PageIndexException();
        if (size < 1) throw new PageSizeException();
        else if (size > 100) size = 10;

        try{
            return listService.getPage(p, size, sortBy);
        } catch (PropertyReferenceException e){
            throw new PageSortException();
        }
    }
}
