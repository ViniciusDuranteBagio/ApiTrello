package com.example.tasks.Controller;

import com.example.tasks.Model.Example;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController // This annotation is used to tell Spring that this class is a controller
@RequestMapping("/api/example") // This annotation is used to tell Spring that this controller will handle requests that start with /api/example
public class ExampleController {
    //This is an example controller with only explanatory purposes

    /**
     * To transform a method in your controller in an endpoint, you need to use the annotations
     * @GetMapping -> Normally used to get data from the server, like consult a list of tasks
     * @PostMapping -> Normally used to send data to the server, like create a new task
     * @PutMapping -> Normally used to update data in the server, like update a task description, or status
     * @DeleteMapping -> Normally used to delete data from the server, like delete a task
     */
    @GetMapping
    public String getEndpoint()
    {
        return "This is a get endpoint, and only returns this string";
    }

    /**
     * @RequestBody annotation is used to tell Spring that the object that will be sent to the server will be in the body of the request
     * 
     * @param example Example object that will be sent to the server
     * @return The same object that was sent to the server
     */
    @PostMapping
    public Example PostEndpoint(@Valid @RequestBody Example example)
    {
        return example;
    }
}
