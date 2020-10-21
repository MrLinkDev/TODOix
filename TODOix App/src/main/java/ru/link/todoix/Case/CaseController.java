package ru.link.todoix.Case;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/todoix")
public class CaseController {
    @Autowired
    private CaseRepository caseRepository;

    @RequestMapping(value = "/case/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createList(@RequestParam UUID listId, @RequestParam String name, @RequestParam String description,@RequestParam  short urgency){
        CaseEntity caseEntity = new CaseEntity(listId, name, description, urgency);
        caseRepository.save(caseEntity);
    }

    @RequestMapping(value = "/case/{case_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public CaseEntity getCase(@PathVariable("case_id") final UUID id){
        return caseRepository.findById(id);
    }

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

    @RequestMapping(value = "/case/{case_id}/mark-down", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void modifyCase(@PathVariable("case_id") final UUID id){
        caseRepository.markDownById(id, new Timestamp(System.currentTimeMillis()));
    }

    @RequestMapping(value = "/case/{case_id}/delete", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCase(@PathVariable("case_id") final UUID id){
        caseRepository.deleteById(id);
    }

    @RequestMapping(value = "/case/all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<CaseEntity> getAll(){
        return caseRepository.findAll();
    }
}
