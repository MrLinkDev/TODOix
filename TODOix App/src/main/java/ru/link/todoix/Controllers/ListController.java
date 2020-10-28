package ru.link.todoix.Controllers;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.link.todoix.PostModels.*;
import ru.link.todoix.Repositories.*;
import ru.link.todoix.HibernateSessionFactory;
import ru.link.todoix.Objects.*;
import ru.link.todoix.Services.ListDAO;

import java.util.*;

/**
 * Контроллер для запросов, связанных со списками дел
 */
@RestController
@RequestMapping("/todoix")

public class ListController {
    private final ListDAO listDAO = new ListDAO();

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private CaseRepository caseRepository;

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

        listDAO.create(listDTO);
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
        ListPostModel out = new ListPostModel(listDAO.findById(id));
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
        ListDTO listDTO = listDAO.findById(id);
        listDTO.setName(name);
        listDTO.setModifyDate(new Date(System.currentTimeMillis()));

        listDAO.update(listDTO);
    }

    /**
     * Удаление списка дел
     * @param id - UUID списка дел
     */
    @DeleteMapping(value = "/list/{listId}/delete")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteList(@PathVariable("listId") final UUID id){
        listDAO.deleteById(id);
    }

    /**
     * Просмотр всех списков дел, нужен был для отладки
     * @return List<ListEntity> - список всех списков, но без дел, привязанных к ним
     */
    @GetMapping(value = "/list/all")
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
    @GetMapping(value = "/review")
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
