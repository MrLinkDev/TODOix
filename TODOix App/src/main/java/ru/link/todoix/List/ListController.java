package ru.link.todoix.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.link.todoix.Case.CaseRepository;

import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/todoix")
public class ListController {

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private CaseRepository caseRepository;

    @RequestMapping(value = "/list/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createList(@RequestParam String name){
        ListEntity listEntity = new ListEntity(name);
        listRepository.save(listEntity);
    }

    @RequestMapping(value = "/list/{list_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ListPostModel getList(@PathVariable("list_id") final UUID id){
        ListPostModel out = new ListPostModel(listRepository.findById(id));
        out.setCases(caseRepository.findByListId(id));
        return out;
    }

    @RequestMapping(value = "/list/{list_id}/modify", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void modifyList(@PathVariable("list_id") final UUID id, @RequestParam String name){
        listRepository.updateById(id, name, new Timestamp(System.currentTimeMillis()));
    }

    @RequestMapping(value = "/list/{list_id}/delete", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteList(@PathVariable("list_id") final UUID id){
        listRepository.deleteById(id);
    }

    @RequestMapping(value = "/list/all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<ListEntity> getAll(){
        return listRepository.findAll();
    }
}
