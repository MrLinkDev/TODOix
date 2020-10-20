package ru.link.todoix.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/todoix")
public class ListController {

    @Autowired
    private ListRepository listRepository;

    @RequestMapping(value = "/list/create", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseStatus(HttpStatus.CREATED)
    public List<ListEntity> createList(@RequestParam String name){
        ListEntity listEntity = new ListEntity(name);
        listRepository.save(listEntity);

        return listRepository.findAll();
    }

    @RequestMapping(value = "/list/{list_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Optional<ListEntity> getList(@PathVariable("list_id") final Long id){
        return listRepository.findById(id);
    }

    @RequestMapping(value = "/list/{list_id}/modify", method = {RequestMethod.PUT, RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public ListEntity modifyList(@PathVariable("list_id") final long id, @RequestParam(value = "name") String name){
        ListEntity listEntity = listRepository.findById(id);
        listEntity.setName(name);
        listRepository.save(listEntity);
        return listRepository.findById(id);
    }

    @RequestMapping(value = "/list/{list_id}/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteList(@PathVariable("list_id") final long id){
        listRepository.deleteById(id);
        return "DELETED";
    }

    @RequestMapping(value = "/list/all", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ListEntity> getAll(){
        return listRepository.findAll();
    }
}
