package com.example.TodoApiSpring;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("anotherTodoService")
//@Primary  // always gets the pirority when to implements the same method or collision occurs

public class AnotherTodoService implements  TodoService{
    @Override
    public String doSomething() {
        return "Something from another todo service";
    }
}
