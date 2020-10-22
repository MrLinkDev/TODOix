package ru.link.todoix.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

/**
 * Контроллер для запросов, связанных со списками дел
 */
@RestController
@RequestMapping("/todoix")

public class Controller {

    @Autowired
    private Repository listRepository;

    @Autowired
    private ru.link.todoix.Case.Repository caseRepository;

    /**
     * Создание списка дел
     * @param name - имя создаваемого списка
     */
    @RequestMapping(value = "/list/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createList(@RequestParam String name){
        Entity listEntity = new Entity(name);
        listRepository.save(listEntity);
    }

    /**
     * Получение списка дел по UUID
     * @param id - UUID списка дел
     * @return ListPostModel, в котором находится список дел
     * и сами дела, привязанные к возвращаемому списку
     */
    @RequestMapping(value = "/list/{list_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ListPostModel getList(@PathVariable("list_id") final UUID id){
        ListPostModel out = new ListPostModel(listRepository.findById(id));
        out.setCases(caseRepository.findByListId(id));
        return out;
    }

    /**
     * Изменение списка дел
     * @param id - UUID списка дел
     * @param name - новое имя списка дел
     */
    @RequestMapping(value = "/list/{list_id}/modify", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void modifyList(@PathVariable("list_id") final UUID id, @RequestParam String name){
        listRepository.updateById(id, name, new Timestamp(System.currentTimeMillis()));
    }

    /**
     * Удаление списка дел
     * @param id - UUID списка дел
     */
    @RequestMapping(value = "/list/{list_id}/delete", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteList(@PathVariable("list_id") final UUID id){
        listRepository.deleteById(id);
    }

    /**
     * Просмотр всех списков дел, нужен был для отладки
     * @return List<ListEntity> - список всех списков, но без дел, привязанных к ним
     */
    @RequestMapping(value = "/list/all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Entity> getAll(){
        return listRepository.findAll();
    }

    /**
    @RequestMapping(value = "/review", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Page<ListPostModel> getReview(){
        ReviewPostModel out = new ReviewPostModel();
        List<ListEntity> lists = listRepository.findAll();
        List<ListPostModel> temp = new ArrayList<>();

        for (int i = 0; i < lists.size(); ++i){
            temp.add(new ListPostModel(lists.get(i)));
        }

        Pageable pageable = PageRequest.of(0, 2);
        final Page<ListPostModel> page = new PageImpl<>(temp, pageable, temp.size());

        int finished = 0, opened = 0;
        for (ListPostModel l : temp){
            l.setCases(caseRepository.findByListId(l.getId()));
            if (l.getOpenedCount() == 0) ++finished;
            else ++opened;
        }
        out.setLists(lists);
        out.setFinishedListCount(finished);
        out.setOpenedListCount(opened);

        return page;
    }*/
}
