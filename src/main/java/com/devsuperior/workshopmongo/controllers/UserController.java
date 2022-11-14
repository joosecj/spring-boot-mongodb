package com.devsuperior.workshopmongo.controllers;

import com.devsuperior.workshopmongo.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAll() {
        User maria = new User("1", "Maria Brown", "maria@Gamil.com");
        User alex = new User("2", "Alex Green", "alex@Gamil.com");
        List<User> userList = new ArrayList<>();
        userList.addAll(Arrays.asList(maria, alex));
        return ResponseEntity.ok(userList);
    }
}
