package com.example.TodoApiSpring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController          // RestController is a combination of the @controller and @ResponseBody
                         // this part of the class is treated as a controller
                         // two use cases collecting the request and returning the response.
                         // Whatever we returned from the function of the controller that will return in the response of the http object
public class TodoController {   // Object creation of todoController class is managed by spring  because of RestController

    private static List<Todo> todoList;



    public TodoController(){
        todoList = new ArrayList<>();
        todoList.add(new Todo(1, false, "Todo 1", 1));     // ← creates and adds a Person object
        todoList.add(new Todo(2, true, "Todo 2", 2));     // ← another Person object
    }

    @GetMapping("/todos")         // get request on the specified url
     public ResponseEntity<List<Todo>> getTodos(){
        return ResponseEntity.ok(todoList);
    }

    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED)

    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo){       // whatever body is coming in the incoming  body int the http request that body should be accessible inside the function
        /**
         * we can use this annotation to set the status code     @ResponseStatus(HttpStatus.CREATED)
         * By using the responseentity class
         * */
        todoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }

    @GetMapping ("/todos/{todoId}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long todoId){
        for(Todo todo: todoList){
            if(todo.getId()==todoId){
                return ResponseEntity.ok(todo);
            }

        }
        return ResponseEntity.notFound().build();
      //  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO_NOT_FOUND);
    }

}
