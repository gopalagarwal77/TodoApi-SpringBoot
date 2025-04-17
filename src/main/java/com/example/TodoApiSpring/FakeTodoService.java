package com.example.TodoApiSpring;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("fakeTodoService")           // now spring will manage it
public class FakeTodoService implements TodoService{  // spring will create a FakeTodoservice object and I want the object in my TodoController so spring will automatically send it
  // concrete class which is implementing TodoService

    public String doSomething(){
        return "Something";
    }
}


// in the todocontroller  we are depending on the interface and which class is implements the interface FakeTodoService
// so it automaticaaly figures out faketodoservice object will be created and need to be passed in the constructor of todocontroller
