package com.orienteering.rest.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.orienteering.rest.demo.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {}
