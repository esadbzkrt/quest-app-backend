package com.esadbzkrt.questapp.services;

import com.esadbzkrt.questapp.entities.Post;
import com.esadbzkrt.questapp.entities.User;
import com.esadbzkrt.questapp.repository.PostRepository;
import com.esadbzkrt.questapp.requests.PostCreateRequest;
import com.esadbzkrt.questapp.requests.PostUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    UserService userService;


    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }


    public List<Post> getAllPosts(Optional<Long> userId) {
        if (userId.isPresent()) {
            return postRepository.findByUserId(userId.get());
        } else {
            return postRepository.findAll();
        }
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createPost(PostCreateRequest newPostRequest) {
        User user = userService.getUser(newPostRequest.getUserId());
        if (user==null){
            return null;
        }else {
            Post post = new Post();
            post.setId(newPostRequest.getId());
            post.setText(newPostRequest.getText());
            post.setTitle(newPostRequest.getTitle());
            post.setUser(user);

            return postRepository.save(post);
        }
    }

    public Post updatePost(Long postId, PostUpdateRequest newPostRequest) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            Post updatedPost = post.get();
            updatedPost.setText(newPostRequest.getText());
            updatedPost.setTitle(newPostRequest.getTitle());
            postRepository.save(updatedPost);
            return updatedPost;
        } else {
            return null;
        }
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
