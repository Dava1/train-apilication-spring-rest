package com.example.myapp.controller;

import com.example.myapp.controller.dto.CreateUserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.myapp.model.User;
import com.example.myapp.service.UserService;

import java.util.List;

import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;
   
   public UserController(UserService service) {
    this.service = service;
   }

   @GetMapping("/")
   public ResponseEntity<List<User>> getAllUsers() {
    return ResponseEntity.ok(service.getAllUsers());
   }
 
   @GetMapping("/{id}")
   public ResponseEntity<User> getUserById(@PathVariable Long id) {
    return service.getUserById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
   }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public ResponseEntity<User> createUser(@RequestBody CreateUserRequest user) {
        return ResponseEntity.ok(service.createUser(user));
   }
    
   @PutMapping("/{id}")
   public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
    return ResponseEntity.ok(service.updateUser(id, user));
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
       service.deleteUser(id);
    return ResponseEntity.noContent().build();    
    }
}
