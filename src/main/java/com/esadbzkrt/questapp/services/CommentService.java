package com.esadbzkrt.questapp.services;

import com.esadbzkrt.questapp.entities.Comment;
import com.esadbzkrt.questapp.entities.Post;
import com.esadbzkrt.questapp.entities.User;
import com.esadbzkrt.questapp.repository.CommentRepository;
import com.esadbzkrt.questapp.requests.CommentCreateRequest;
import com.esadbzkrt.questapp.requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            return commentRepository.findAllByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findAllByUserId(userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findAllByPostId(postId.get());
        } else {
            return commentRepository.findAll();
        }
    }

    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createComment(CommentCreateRequest request) {
        User user = userService.getUser(request.getUserId());
        Post post = postService.getPostById(request.getPostId());

        if (user == null || post == null) {
            return null;
        } else {
            Comment comment = new Comment();
            comment.setId(request.getId());
            comment.setText(request.getText());
            comment.setUser(user);
            comment.setPost(post);

            return commentRepository.save(comment);
        }
    }


    public Comment updateComment(Long commentId, CommentUpdateRequest request) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            Comment updatedComment = comment.get();
            updatedComment.setText(request.getText());
            commentRepository.save(updatedComment);
            return updatedComment;
        } else {
            return null;
        }
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}

