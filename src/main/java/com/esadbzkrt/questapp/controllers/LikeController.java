package com.esadbzkrt.questapp.controllers;

import com.esadbzkrt.questapp.entities.Comment;
import com.esadbzkrt.questapp.entities.Like;
import com.esadbzkrt.questapp.requests.LikeCreateRequest;
import com.esadbzkrt.questapp.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<Like> getAllLikes(Optional<Long> userId, Optional<Long> postId) {
        return likeService.getAllLikesWithParam(userId, postId);
    }

    @GetMapping("/{likeId}")
    public Like getLikeById(@PathVariable Long likeId) {
        return likeService.getLikeById(likeId);
    }

    @PostMapping
    public Like createLike(@RequestBody LikeCreateRequest request) {
        return likeService.createLike(request);
    }

    @PutMapping("/{likeId}")
    public Like updateLike(@PathVariable Long likeId, @RequestBody LikeCreateRequest request) {
        return likeService.updateLike(likeId, request);
    }



    @DeleteMapping("/{likeId}")
    public void deleteLike(@PathVariable Long likeId) {
        likeService.deleteLike(likeId);
    }


}
