package com.orienteering.rest.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.orienteering.rest.demo.model.User;

import java.util.Optional;

/**
 *Repository for User objects
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    /**
     * Query users by email
     * @param userEmail
     * @return
     */
    Optional<User> findByUserEmail(String userEmail);

    /**
     * Query if user exists by email
     * @param userEmail
     * @return
     */
    Boolean existsByUserEmail(String userEmail);
}
