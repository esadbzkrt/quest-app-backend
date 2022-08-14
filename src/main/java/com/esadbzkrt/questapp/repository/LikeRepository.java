package com.esadbzkrt.questapp.repository;
import com.esadbzkrt.questapp.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {


    List<Like> findAllByUserIdAndPostId(Long userId, Long postId);

    List<Like> findAllByUserId(Long userId);

    List<Like> findAllByPostId(Long postId);
}
