package com.example.TodoApiSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController          // RestController is a combination of the @controller and @ResponseBody
                         // this part of the class is treated as a controller
                         // two use cases collecting the request and returning the response.
                         //  Whatever we returned from the function of the controller that will return in the response of the http object

@RequestMapping("/api/v1/todos")
public class TodoController {   // Object creation of todoController class is managed by spring  because of RestController

    private static List<Todo> todoList;

    //  private FakeTodoService todoService; // instead of depending on the concrete class i should depend on abstraction // aggregatation has a relationship  (composition) instance of another class a class property
   // we have to manage that from where the todoService came from
   // instead of depending on the concrete class I have to depend on abstraction

  //  @Autowired   // no need to pass the dependency in the constructor it will do field injection
  //  @Qualifier("fakeTodoService")  //  injecting fakeTodoService
    private TodoService todoService;    // depend on interface    // todoService is a instance variable of type TodoService    // the type of the variable, which is likely a class or interface
    private TodoService todoService2;


    public TodoController(/*FakeTodoService*/ @Qualifier("anotherTodoService") TodoService todoService,
                                              @Qualifier("fakeTodoService") TodoService todoService2){ // whenever the spring will initialise this todoController I want spring to  actually inject an object , pass or send an object of FakeTodoService to this controller function
        this.todoService =  todoService;      // we can also inject multiple dependency in the constructor
        this.todoService2 =  todoService2;
        todoList = new ArrayList<>();
        todoList.add(new Todo(1, false, "Todo 1", 1));     // ← creates and adds a Person object
        todoList.add(new Todo(2, true, "Todo 2", 2));     // ← another Person object
     //   this.todoService = new FakeTodoService(); // I am managing the creation of to do class because spring does not know anything about the todoService class
    }

    @GetMapping       // get request on the specified url
     public ResponseEntity<List<Todo>> getTodos(@RequestParam (required = false) Boolean isCompleted){ // Spring boot is handling the part of conversion
        // requires(true/false) if we do not want to send the query params Default("true")
        System.out.println("Incoming query param is: " + isCompleted + " " + this.todoService.doSomething());
        return ResponseEntity.ok(todoList);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo){       // whatever body is coming in the incoming  body int the http request that body should be accessible inside the function
        /**
         * we can use this annotation to set the status code     @ResponseStatus(HttpStatus.CREATED)
         * By using the responseentity class
         * */
        todoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }

    @GetMapping ("/{todoId}")
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
