package ru.link.todoix.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ListController {

    private ListDAO listDAO;

    @RequestMapping(value = "/create", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseStatus(HttpStatus.CREATED)
    public List<ListItem> createList(@RequestParam String name){
        listDAO = new ListDAO();
        listDAO.create(new ListItem(name));
        return listDAO.getAll();
    }


}
