package com.esadbzkrt.questapp.services;

import com.esadbzkrt.questapp.entities.Comment;
import com.esadbzkrt.questapp.entities.Like;
import com.esadbzkrt.questapp.entities.Post;
import com.esadbzkrt.questapp.entities.User;
import com.esadbzkrt.questapp.repository.LikeRepository;
import com.esadbzkrt.questapp.requests.LikeCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private UserService userService;
    private PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Like> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            return likeRepository.findAllByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return likeRepository.findAllByUserId(userId.get());
        } else if (postId.isPresent()) {
            return likeRepository.findAllByPostId(postId.get());
        } else {
            return likeRepository.findAll();
        }

    }

    public Like getLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public void deleteLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }

    public Like createLike(LikeCreateRequest request) {
        User user = userService.getUser(request.getUserId());
        Post post = postService.getPostById(request.getPostId());

        if (user == null || post == null) {
            return null;
        } else {
            Like like = new Like();
            like.setId(request.getId());
            like.setUser(user);
            like.setPost(post);

            return likeRepository.save(like);
        }
    }

    public Like updateLike(Long likeId, LikeCreateRequest request) {
        Optional<Like> like = likeRepository.findById(likeId);
        if (like.isPresent()) {
            Like updatedLike = like.get();
            updatedLike.setId(request.getId());
            updatedLike.setUser(userService.getUser(request.getUserId()));
            updatedLike.setPost(postService.getPostById(request.getPostId()));
            return likeRepository.save(updatedLike);
        } else {
            return null;
        }
    }
}
