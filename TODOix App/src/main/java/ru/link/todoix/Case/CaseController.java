package ru.link.todoix.Case;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.link.todoix.List.*;

import java.util.*;

@RestController
@RequestMapping("/todoix/list")
public class CaseController {
    @Autowired
    private CaseRepository caseRepository;

    @RequestMapping(value = "/{list_id}/case/create", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseStatus(HttpStatus.CREATED)
    public List<CaseEntity> createList(@RequestParam String name,@RequestParam  String description,@RequestParam  short urgency){
        CaseEntity caseEntity = new CaseEntity(name, description, urgency);
        caseRepository.save(caseEntity);

        return caseRepository.findAll();
    }

    @RequestMapping(value = "/list/{list_id}/case/{case_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Optional<CaseEntity> getList(@PathVariable("list_id") final Long id){
        return caseRepository.findById(id);
    }

    @RequestMapping(value = "list/{list_id}/case/{case_id}/modify", method = {RequestMethod.PUT, RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public CaseEntity modifyList(@PathVariable("list_id") final long id, @RequestParam(value = "name") String name){
        CaseEntity caseEntity = caseRepository.findById(id);
        caseEntity.setName(name);
        caseRepository.save(caseEntity);
        return caseRepository.findById(id);
    }

    @RequestMapping(value = "/list/{list_id}/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteList(@PathVariable("list_id") final long id){
        caseRepository.deleteById(id);
        return "DELETED";
    }

    @RequestMapping(value = "/list/all", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CaseEntity> getAll(){
        return caseRepository.findAll();
    }
}
