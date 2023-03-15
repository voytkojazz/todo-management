package com.encoway.todomanagement.todo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@SessionAttributes("name")
public class ToDoController {

    private ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    //list-todos
    @RequestMapping("/list-todos")
    public String listAllToDos(ModelMap map) {
        String username = getLoggedUsername(map);
        map.put("todos", toDoService.findByUsername(username));
        return "listToDos";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model) {
        String username = getLoggedUsername(model);
        ToDo todo = new ToDo(0, username, "Default Description", LocalDate.now().plusYears(1), false);
        model.put("toDo", todo);
        return "addTodo";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap modelMap, @Valid @ModelAttribute("toDo") ToDo toDo, BindingResult result) {
        if(result.hasErrors()) {
            return "addTodo";
        }
        String username = getLoggedUsername(modelMap);
        toDoService.addTodo(
                toDo.getDescription(),
                username,
                toDo.getTargetDate(),
                false
        );
        return "redirect:list-todos";
    }

    @RequestMapping("delete-todo")
    public String deleteTodo(
            @RequestParam int id
    )
    {
        toDoService.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(ModelMap modelMap, @RequestParam int id)
    {
        ToDo toDo = toDoService.findById(id);
        modelMap.addAttribute("toDo", toDo);
        return "addTodo";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model,
                             @Valid @ModelAttribute("toDo") ToDo toDo,
                             BindingResult result)
    {
        if (result.hasErrors()) {
            return "addTodo";
        }
        String username = getLoggedUsername(model);
        toDo.setUsername(username);
        toDoService.updateTodo(toDo);
        return "redirect:list-todos";
    }

    private String getLoggedUsername(ModelMap map) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}
