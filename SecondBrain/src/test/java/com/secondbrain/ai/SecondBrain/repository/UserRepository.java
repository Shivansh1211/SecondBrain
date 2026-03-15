package com.secondbrain.ai.SecondBrain.repository;

import com.secondbrain.ai.SecondBrain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
