package com.example.TodoApiSpring;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController          // RestController is a combination of the @controller and @ResponseBody
                         // this part of the class is treated as a controller
                         // two use cases collecting the request and returning the response.
                         // Whatever we returned from the function of the controller that will return in the response of the http object
public class TodoController {

    private static List<Todo> todos;

    public TodoController(){
        todos = new ArrayList<>();
        todoList.add(new Todo(1, false, "Todo 1", 1));     // ← creates and adds a Person object
        todoList.add(new Todo(2, true, "Todo 2", 2));     // ← another Person object
    }

}
