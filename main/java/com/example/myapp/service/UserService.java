package com.example.myapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.myapp.controller.dto.CreateUserRequest;
import com.example.myapp.controller.dto.UpdateUserRequest;
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
        createdUser.setUserName(user.username());
        createdUser.setEmail(user.email());
        createdUser.setCreatedAt(LocalDate.now());
        return repository.save(createdUser);
    }

    @Transactional
    public User updateUser(Long id, UpdateUserRequest userDetail){
       return repository.findById(id).map(curUser ->{
           curUser.setUserName(userDetail.username());
           curUser.setEmail(userDetail.email());
           return repository.save(curUser); 
       }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public void deleteUser(Long userId){
        repository.deleteById(userId);
    }
    
    private User convertToUser(UpdateUserRequest updated){
        User user = new User();
        
        return user;
    }
}
