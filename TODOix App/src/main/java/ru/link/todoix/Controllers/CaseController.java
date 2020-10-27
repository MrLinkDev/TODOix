package ru.link.todoix.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.link.todoix.Repositories.*;
import ru.link.todoix.Repositories.ListRepository;
import ru.link.todoix.Objects.CaseEntity;

import java.sql.Timestamp;
import java.util.*;

/**
 * Контроллер для запросов, связанных с делами
 */
@RestController
@RequestMapping("/todoix")
public class CaseController {
    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private ListRepository listRepository;

    /**
     * Создание дела
     * @param listId - UUID списка дел, к которому будет привязано дела
     * @param name - название дела
     * @param description - описание дела
     * @param priority - срочность дела
     */
    @PostMapping(value = "/case/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCase(@RequestParam UUID listId, @RequestParam String name, @RequestParam String description,@RequestParam  short priority){
        CaseEntity caseEntity = new CaseEntity(listRepository.findById(listId), name, description, priority);
        caseEntity.setCreateDate(new Date(System.currentTimeMillis()));
        caseEntity.setModifyDate(new Date(System.currentTimeMillis()));
        caseRepository.save(caseEntity);
    }

    /**
     * Получение дела по его UUID
     * @param id - UUID дела
     * @return сущность дела
     */
    @GetMapping(value = "/case/{caseId}")
    @ResponseStatus(HttpStatus.OK)
    public CaseEntity getCase(@PathVariable("caseId") final UUID id){
        return caseRepository.findById(id);
    }

    /**
     * Изменение записи о деле по его UUID
     * @param id - UUID дела
     * @param name - новое название дела
     * @param description - новое описание дела
     * @param priority - новая срочность дела
     * @param finished - новый статус дела
     */
    @PutMapping(value = "/case/{caseId}/modify")
    @ResponseStatus(HttpStatus.OK)
    public void modifyCase(@PathVariable("caseId") final UUID id, @RequestParam(required = false) String name, @RequestParam(required = false) String description, @RequestParam(required = false) Short priority, @RequestParam(required = false) Boolean finished){
        CaseEntity caseEntity = caseRepository.findById(id);
        caseRepository.updateById(
                id,
                name == null ? caseEntity.getName() : name,
                description == null ? caseEntity.getDescription() : description,
                priority == null ? caseEntity.getPriority() : priority,
                finished == null ? caseEntity.isFinished() : finished,
                new Date(System.currentTimeMillis())
        );
    }

    /**
     * Присвоение делу статуса завершённого
     * @param id - UUID дела
     */
    @PutMapping(value = "/case/{caseId}/markDown")
    @ResponseStatus(HttpStatus.OK)
    public void markDownCase(@PathVariable("caseId") final UUID id){
        caseRepository.markDownById(id, new Timestamp(System.currentTimeMillis()));
    }

    /**
     * Удаление дела по его UUID
     * @param id - UUID дела
     */
    @DeleteMapping(value = "/case/{caseId}/delete")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCase(@PathVariable("caseId") final UUID id){
        caseRepository.deleteById(id);
    }

    /**
     * Получение всех дел, нужен был для отладки
     * @return List<Entity>
     */
    @GetMapping(value = "/case/all")
    @ResponseStatus(HttpStatus.OK)
    public List<CaseEntity> getAll(){
        return caseRepository.findAll();
    }

}
