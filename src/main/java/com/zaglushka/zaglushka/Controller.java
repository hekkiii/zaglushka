package com.zaglushka.zaglushka;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    //GET
    @GetMapping(value = "/getting")
    public Getting getting(){
        return new Getting();
    }

    //POST
    @PostMapping(value = "/posting", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Posting createUser(@RequestBody Posting posting){
        return posting;
    }
}
