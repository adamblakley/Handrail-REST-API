package com.orienteering.rest.demo.repository;

import com.orienteering.rest.demo.model.UserPhotograph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPhotographRepository extends JpaRepository<UserPhotograph, Long> {
}
