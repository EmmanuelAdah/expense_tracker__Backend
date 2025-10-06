package com.expensetracker.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.expensetracker.data.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    void deleteByUsername(String username);

    boolean existsByUsername(String username);

    Optional<User> findById(long userId);

    Optional<User> findByEmail(String email);
}
