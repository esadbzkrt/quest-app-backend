package com.esadbzkrt.questapp.repository;
import com.esadbzkrt.questapp.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByUserIdAndPostId(Long userId, Long postId);

    List<Comment> findAllByUserId(Long userId);

    List<Comment> findAllByPostId(Long postId);
}

