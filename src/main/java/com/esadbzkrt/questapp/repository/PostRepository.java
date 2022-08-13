package com.esadbzkrt.questapp.repository;

import com.esadbzkrt.questapp.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {



    List<Post> findByUserId(Long userId);
}

