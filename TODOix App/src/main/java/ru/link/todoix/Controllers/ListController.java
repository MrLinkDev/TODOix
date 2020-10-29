package ru.link.todoix.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.link.todoix.PostModels.*;
import ru.link.todoix.Repositories.*;
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
    public void createList(@RequestParam String name){
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
     * @return ListPostModel, в котором находится список дел
     * и сами дела, привязанные к возвращаемому списку
     */
    @GetMapping(value = "/list/{listId}")
    @ResponseStatus(HttpStatus.OK)
    public ListPostModel getList(@PathVariable("listId") final UUID id){
        ListPostModel out = new ListPostModel(listService.findById(id));
        out.setTasks(taskService.findByList(listService.findById(id)));
        //TODO: поиск дел по id списка

        return out;
    }

    /**
     * Изменение списка дел
     * @param id - UUID списка дел
     * @param name - новое имя списка дел
     */
    @PutMapping(value = "/list/{listId}/modify")
    @ResponseStatus(HttpStatus.OK)
    public void modifyList(@PathVariable("listId") final UUID id, @RequestParam String name){
        ListDTO listDTO = listService.findById(id);
        listDTO.setName(name);
        listDTO.setModifyDate(new Date(System.currentTimeMillis()));

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
    public ReviewPostModel getReview(@RequestParam(required = false) Integer p, @RequestParam(required = false) Integer size, @RequestParam(required = false) String sortBy){
        ReviewPostModel review = listService.getPage(
                p == null ? 0 : p - 1,
                size == null ? 10 : size > 100 ? 10 : size,
                sortBy == null ? "name" : sortBy
        );

        return review;
    }
}
