package com.devsuperior.workshopmongo.controllers;

import com.devsuperior.workshopmongo.dto.UserDTO;

import com.devsuperior.workshopmongo.entities.Post;
import com.devsuperior.workshopmongo.entities.User;
import com.devsuperior.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(userService.findAll(pageable));
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserDTO> inset(@RequestBody UserDTO userDTO) {
        userDTO = userService.insert(userDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(userDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(userDTO);
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return  ResponseEntity.noContent().build();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserDTO> update(@PathVariable String id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok().body(userService.update(id, userDTO));
    }

    @RequestMapping(value="/{id}/posts", method=RequestMethod.GET)
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
        User obj = userService.findByPosts(id);
        return ResponseEntity.ok().body(obj.getPosts());
    }
}
