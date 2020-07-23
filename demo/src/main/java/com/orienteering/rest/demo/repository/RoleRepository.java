package com.orienteering.rest.demo.repository;

import com.orienteering.rest.demo.ERole;
import com.orienteering.rest.demo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByRole(ERole eRole);
}
