package com.esadbzkrt.questapp.repository;

import com.esadbzkrt.questapp.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}

