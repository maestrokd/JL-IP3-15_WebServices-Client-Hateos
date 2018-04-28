package com.infoPulse.lessons.model.repository;

import com.infoPulse.lessons.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RoleRepository extends JpaRepository<Role, Integer> {

    // findBy[real field name]!!!
    Role findRoleByName(String name);

    Role findRoleById(int id);

}



