package com.encoway.todomanagement.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ToDoControllerJpa {

    private ToDoRepository toDoRepository;

    public ToDoControllerJpa(ToDoRepository toDoRepository) {
        super();
        this.toDoRepository = toDoRepository;
    }

    @RequestMapping(value = "list-todos", method = RequestMethod.GET)
    public String listAllTodos(ModelMap model) {
        String username = getLoggedUsername(model);
        List<ToDo> toDoList = toDoRepository.findByUsername(username);
        model.put("todos", toDoList);
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
    public String addTodo(
            ModelMap model,
            @Valid @ModelAttribute("toDo") ToDo toDo,
            BindingResult result)
    {
        if (result.hasErrors()) {
            return "addTodo";
        }
        String username = getLoggedUsername(model);
        toDo.setUsername(username);
        toDoRepository.save(toDo);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "delete-todo")
    public String deleteTodo(@RequestParam int id) {
        toDoRepository.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(ModelMap modelMap, @RequestParam int id)
    {
        ToDo toDo = toDoRepository.findById(id).orElseGet(null);
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
        toDoRepository.save(toDo);
        return "redirect:list-todos";
    }

    private String getLoggedUsername(ModelMap map) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
