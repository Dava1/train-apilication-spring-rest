package com.example.myapp.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.model.Post;
import com.example.myapp.service.PostService;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService service;

    public PostController(PostService service){
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<Post>> getAllPost() {
        return ResponseEntity.ok(service.getAllPost());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Post> getMethodName(@PathVariable Long id) {
        return service.getPostById(id)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post){
        return ResponseEntity.ok(service.createPost(post));   
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post postDetail){
        return ResponseEntity.ok(service.updatePost(id, postDetail));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id){
        service.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
