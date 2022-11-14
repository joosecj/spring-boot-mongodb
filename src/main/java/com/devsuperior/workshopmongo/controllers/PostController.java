package com.devsuperior.workshopmongo.controllers;

import com.devsuperior.workshopmongo.entities.Post;
import com.devsuperior.workshopmongo.services.PostService;
import com.devsuperior.workshopmongo.controllers.util.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(value ="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Post> findById(@PathVariable String id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @RequestMapping(value ="/titlesearch", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue = "") String text) {
        text = URL.decodeParam(text);
        return ResponseEntity.ok(postService.findByTitle(text));
    }
}
