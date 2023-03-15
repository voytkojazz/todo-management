package com.encoway.todomanagement.todo;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class ToDoService {
    private static List<ToDo> todos = new ArrayList<>();
    public static int todosCount = 0;
    static {
        todos.add(new ToDo(todosCount++, "voytkojazz", "LearnJs", LocalDate.now().plusYears(1), false));
        todos.add(new ToDo(todosCount++, "voytkojazz", "Learn DevOps", LocalDate.now().plusYears(2), false));
        todos.add(new ToDo(todosCount++, "voytkojazz", "Learn Full Stack Development", LocalDate.now().plusYears(3), false));
    }

    public List<ToDo> findByUsername(String username) {
        return todos.stream().filter(todo -> todo.getUsername().equalsIgnoreCase(username)).toList();
    }

    public void addTodo(String description, String username, LocalDate targetDate, boolean done) {
        ToDo newTodo = new ToDo(
                todosCount++,
                username,
                description,
                targetDate,
                done
        );
        todos.add(newTodo);
    }


    public void deleteById(int id) {
        Predicate<? super ToDo> predicate = (todo -> todo.getId() == id);
        todos.removeIf(predicate);
    }

    public ToDo findById(int id) {
        return todos.stream().filter(todo -> todo.getId() == id).findFirst().orElse(null);
    }

    public void updateTodo(ToDo toDo) {
        deleteById(toDo.getId());
        todos.add(toDo);
    }
}
