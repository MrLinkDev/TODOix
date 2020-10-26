package ru.link.todoix.Case;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

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


    // TODO: 2 дополнения сложу а один комментарий:
    //  1. конроллеры ничего не долны знать о сущностях модели, для того чтоб конроллер мог оправлять/получать данные в
    //      виде объектов есть DTO (можно в гугле спросить "DTO зачем нужны")
    //  2. все действия с объектами в том числе через репозитори нужнь вынести в сервисы и желательно через интерфейсы,
    //      например CaseService и CaseServiceImpl/ таким образом мы всегда сможем легко подменить реализацию, а вместе
    //      с ней и зранилку и другую бизнес-логику и т.д.

    //  TODO: @RequestMapping можно укоротить за счет использования GetMappint/PostMapping и т.д. запись будет короче

    /**
     * Создание дела
     * @param listId - UUID списка дел, к которому будет привязано дела
     * @param name - название дела
     * @param description - описание дела
     * @param urgency - срочность дела
     */
    @RequestMapping(value = "/case/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createList(@RequestParam UUID listId, @RequestParam String name, @RequestParam String description,@RequestParam  short urgency){
        CaseEntity caseEntity = new CaseEntity(listId, name, description, urgency);
        caseRepository.save(caseEntity);
    }

    /**
     * Получение дела по его UUID
     * @param id - UUID дела
     * @return сущность дела
     */
    @RequestMapping(value = "/case/{case_id}", method = RequestMethod.GET) // TODO: "case_id" лучше в путях использовать camelCase
    @ResponseStatus(HttpStatus.OK)
    public CaseEntity getCase(@PathVariable("case_id") final UUID id){
        return caseRepository.findById(id);
    }

    /**
     * Изменение записи о деле по его UUID
     * @param id - UUID дела
     * @param name - новое название дела
     * @param description - новое описание дела
     * @param urgency - новая срочность дела
     * @param finished - новый статус дела
     */
    @RequestMapping(value = "/case/{case_id}/modify", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void modifyCase(@PathVariable("case_id") final UUID id, @RequestParam(required = false) String name, @RequestParam(required = false) String description, @RequestParam(required = false) Short urgency, @RequestParam(required = false) Boolean finished){
        CaseEntity caseEntity = caseRepository.findById(id);
        caseRepository.updateById(
                id,
                name == null ? caseEntity.getName() : name,
                description == null ? caseEntity.getDescription() : description,
                urgency == null ? caseEntity.getUrgency() : urgency,
                finished == null ? caseEntity.isFinished() : finished,
                new Timestamp(System.currentTimeMillis())
        );
    }

    /**
     * Присвоение делу статуса завершённого
     * @param id - UUID дела
     */
    @RequestMapping(value = "/case/{case_id}/mark-down", method = RequestMethod.PUT) // TODO: хоть в задании
    // и было "mark-down", но лучше чтоб было одинково - везде используйте camelCase
    @ResponseStatus(HttpStatus.OK)
    public void markDownCase(@PathVariable("case_id") final UUID id){
        caseRepository.markDownById(id, new Timestamp(System.currentTimeMillis()));
    }

    /**
     * Удаление дела по его UUID
     * @param id - UUID дела
     */
    @RequestMapping(value = "/case/{case_id}/delete", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCase(@PathVariable("case_id") final UUID id){
        caseRepository.deleteById(id);
    }

    /**
     * Получение всех дел, нужен был для отладки
     * @return List<Entity>
     */
    @RequestMapping(value = "/case/all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<CaseEntity> getAll(){
        return caseRepository.findAll();
    }

    // TODO: а как посмотерть все дела в списке, с пигинацией и сортировкой, пользовтель явно же захочет это сделать
}
