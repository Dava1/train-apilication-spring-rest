package com.example.myapp.service;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myapp.model.Post;
import com.example.myapp.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository repository;
    
    public List<Post> getAllPost(){
        return repository.findAll();
    }

    public Optional<Post> getPostById(Long postId){
        return repository.findById(postId);
    }

    @Transactional
    public Post createPost(Post post){
        if(repository.findById(post.getId()).isPresent()){
            throw new RuntimeException("Post alreary exist");    
        }
        return repository.save(post);
    }

    @Transactional
    public Post updatePost(Long id, Post post) {
        return repository.findById(id).map(curPost ->{
            curPost.setContent(post.getContent());
            curPost.setTitle(post.getTitle());
            return repository.save(curPost);    
        }).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    @Transactional
    public void deletePost(Long postId){
        repository.deleteById(postId);
    }
}
