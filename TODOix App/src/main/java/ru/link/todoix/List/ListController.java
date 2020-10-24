package ru.link.todoix.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.link.todoix.Case.*;
import ru.link.todoix.HibernateSessionFactory;

import java.sql.Timestamp;
import java.util.*;

/**
 * Контроллер для запросов, связанных со списками дел
 */
@RestController
@RequestMapping("/todoix")

public class ListController {

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private CaseRepository caseRepository;

    /**
     * Создание списка дел
     * @param name - имя создаваемого списка
     */
    @RequestMapping(value = "/list/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createList(@RequestParam String name){
        ListEntity listEntity = new ListEntity(name);
        //listEntity.setListId(UUID.randomUUID());
        //listRepository.save(listEntity);
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
    public List<ListEntity> getAll(){
        return listRepository.findAll();
    }

    /**
     * Получение списка списков дел
     * @param p - страница, которую нужно получить
     * @param size - размер страницы (количество элементов на ней)
     * @param sortBy - параметр сортировки (name, listId, createDate, modifyDate)
     * @return ReviewPostModel - список списков дел с количеством завершённых и открытых списков дел
     */
    @RequestMapping(value = "/review", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ReviewPostModel getReview(@RequestParam(required = false) Integer p, @RequestParam(required = false) Integer size, @RequestParam(required = false) String sortBy){
        Pageable pageable = PageRequest.of(
                p == null ? 0 : p - 1,
                size == null ? 10 : size > 100 ? 10 : size,
                Sort.by(sortBy == null ? "name" : sortBy)
        );
        Page<ListEntity> page = listRepository.findAll(pageable);

        int finishedListCount = 0;
        int openedListCount = 0;

        for (ListEntity list : page.getContent()){
            boolean finished = true;
            List<CaseEntity> cases = caseRepository.findByListId(list.getListId());
            for (CaseEntity caseItem : cases){
                if (!caseItem.isFinished()) {
                    finished = false;
                    break;
                }
            }
            if (finished) ++finishedListCount;
            else ++openedListCount;
        }

        ReviewPostModel review = new ReviewPostModel();
        review.setLists(page.getContent());
        review.setOpenedListCount(openedListCount);
        review.setFinishedListCount(finishedListCount);

        return review;
    }
}
