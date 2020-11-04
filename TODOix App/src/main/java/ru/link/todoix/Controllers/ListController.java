package ru.link.todoix.Controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.link.todoix.DTO.*;
import ru.link.todoix.Exceptions.PageExceptions.*;
import ru.link.todoix.Exceptions.TaskListExceptions.*;
import ru.link.todoix.Services.*;

import java.util.*;

/**
 * Контроллер для запросов, связанных со списками дел
 */
@RestController
@RequestMapping("/todoix")
public class ListController {

    @Autowired
    private ListServiceImpl listService;

    /**
     * Создание списка дел
     *
     * @param name - имя создаваемого списка
     */
    @PostMapping(value = "/list/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createList(@RequestParam String name) {
        listService.create(name);
    }

    /**
     * Получение списка дел по UUID
     *
     * @param id - UUID списка дел
     * @return ListModel, в котором находится список дел
     * и сами дела, привязанные к возвращаемому списку
     */
    @GetMapping(value = "/list/{listId}")
    @ResponseStatus(HttpStatus.OK)
    public ListModelDTO getList(@PathVariable("listId") final UUID id) throws TaskListNotFoundException {
        return listService.getList(id);
    }

    /**
     * Изменение списка дел
     *
     * @param id   - UUID списка дел
     * @param name - новое имя списка дел
     */
    @PutMapping(value = "/list/{listId}/modify")
    @ResponseStatus(HttpStatus.OK)
    public void modifyList(@PathVariable("listId") final UUID id, @RequestParam String name) throws TaskListEmptyNameException, TaskListNotFoundException {
        listService.update(id, name);

    }

    /**
     * Удаление списка дел
     *
     * @param id - UUID списка дел
     */
    @DeleteMapping(value = "/list/{listId}/delete")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteList(@PathVariable("listId") final UUID id) throws TaskListNotFoundException {
        listService.deleteById(id);
    }

    /**
     * Просмотр всех списков дел, нужен был для отладки
     *
     * @return List<ListDTO> - список всех списков, но без дел, привязанных к ним
     */
    @GetMapping(value = "/list/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ListDTO> getAll(){
        return listService.getAll();
    }

    /**
     * Получение списка списков дел
     *
     * @param p      - страница, которую нужно получить
     * @param size   - размер страницы (количество элементов на ней)
     * @param sortBy - параметр сортировки (name, listId, createDate, modifyDate)
     * @return ReviewPostModel - список списков дел с количеством завершённых и открытых списков дел
     */
    @GetMapping(value = "/review")
    @ResponseStatus(HttpStatus.OK)
    public ReviewModelDTO getReview(@RequestParam(defaultValue = "0") Integer p, @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "name") String sortBy)
            throws PageIndexException, PageSizeException, PageSortException {
        return listService.getPage(p, size, sortBy);
    }
}
