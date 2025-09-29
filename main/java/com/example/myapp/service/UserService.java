package com.example.myapp.service;

import java.util.List;
import java.util.Optional;

import com.example.myapp.controller.dto.CreateUserRequest;
import com.example.myapp.controller.dto.CreateUserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myapp.model.User;
import com.example.myapp.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public Optional<User> getUserById(Long userId){
        return repository.findById(userId);
    }
    
    
    
    @Transactional
    public User createUser(CreateUserRequest user){
//       if (repository.findById(user.getId()).isPresent()) {
//            throw new RuntimeException("User already exist");
//       }
        
        var createdUser = new User();
        createdUser.setUserName(user.name());
        createdUser.setEmail(user.email());
        
        return repository.save(createdUser);
    }

    @Transactional
    public User updateUser(Long userId, User userDetail){
       return repository.findById(userId).map(curUser ->{
           curUser.setUserName(userDetail.getUserName());
           curUser.setEmail(userDetail.getEmail());
           return repository.save(curUser); 
       }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public void deleteUser(Long userId){
        repository.deleteById(userId);
    }
}
