package com.expensetracker.data.repositories;

import com.expensetracker.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    void deleteByUsername(String username);

    boolean existsByUsername(String username);

    Optional<User> findById(Long userId);

    Optional<User> findByEmail(String email);
}
