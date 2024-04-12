package de.schulung.todoTogether.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import de.schulung.todoTogether.Model.ToDo;
import de.schulung.todoTogether.Model.ToDoRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class ToDoController {

    @Autowired
    ToDoRepository toDoRepository;

    @GetMapping("/todo")
    public String getToDos(Model model) {
        model.addAttribute("todoList", toDoRepository.findAll());
        model.addAttribute("todo", new ToDo());
        return "todo";
    } 

    @GetMapping("/todo/{id}")
    public String getEditForm(@PathVariable Long id,  Model model){
        model.addAttribute("todo", toDoRepository.findById(id));
        return "edit"; 
    }

    @PutMapping("/todo/{id}")
    public String putEditForm(@PathVariable Long id, @ModelAttribute ToDo todo){
        todo.setId(id);
        toDoRepository.save(todo);
        return "redirect:/todo";
    }

    //POST / -> POST/todo (ToDo Object)
    @PostMapping("/todo")
    public String postMethodName(@ModelAttribute ToDo todo) {
        todo.setStatus(false);
        toDoRepository.save(todo);
        return "redirect:/todo";
    }
    
    //DELETE /todo/{id} -> Todo mit id löschen
    @DeleteMapping("/todo/{id}")
    public String deleteTodo(@PathVariable Long id){
        toDoRepository.deleteById(id);
        return "redirect:/todo";
    }
    
}