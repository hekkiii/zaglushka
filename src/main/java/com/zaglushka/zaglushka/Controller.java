package com.zaglushka.zaglushka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class Controller {
    //GET
    @GetMapping("/user")
    public ResponseEntity<?> selectUser(User user, @RequestParam String login) {
        try {
            Actions actions = new Actions();
            user = actions.selectUser(login);
            if (user == null){
                throw new Exception();
            }
            Files files = new Files();
            files.FileIn(user.toString());
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("No such user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<?> insertUser(@RequestBody String body) throws JsonProcessingException {
        Actions actions = new Actions();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        User user = objectMapper.readValue(body, User.class);
        try{
            String insert = actions.insertUser(user);
            if (insert == null){
                throw new SQLException();
            }
            return new ResponseEntity<>(insert, HttpStatus.CREATED);
        } catch (SQLException e){
            return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/randuser")
    public ResponseEntity<?> randomUser(){
        Files files = new Files();
        return new ResponseEntity<>(files.FileOut(), HttpStatus.OK);
    }
}
