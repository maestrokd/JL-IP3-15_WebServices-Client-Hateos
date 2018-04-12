package com.infoPulse.lessons.model.service;

import com.infoPulse.lessons.model.entity.Role;

import com.infoPulse.lessons.model.repository.RoleRepository;

import com.infoPulse.lessons.model.repository.UserRepository;
import com.infoPulse.lessons.model.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    // Fields
    private UserRepository userRepository;
    private RoleRepository roleRepository;


    // Setters
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    // Methods
    public List<Role> findAll() {
        List<Role> roleList = roleRepository.findAll();
        System.out.println("RoleService.findAll: " + roleList);
        return roleList;
    }

}
