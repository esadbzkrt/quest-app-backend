package com.esadbzkrt.questapp.repository;

import com.esadbzkrt.questapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

