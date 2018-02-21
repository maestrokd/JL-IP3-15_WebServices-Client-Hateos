package com.infoPulse.lessons.model.service;

import com.infoPulse.lessons.model.dto.UserDto;
import com.infoPulse.lessons.model.entity.Role;

import com.infoPulse.lessons.model.entity.User;

import com.infoPulse.lessons.model.entity.VerificationToken;
import com.infoPulse.lessons.model.repository.RoleRepository;

import com.infoPulse.lessons.model.repository.UserRepository;
import com.infoPulse.lessons.model.repository.VerificationTokenRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl {

    // Fields

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private VerificationTokenRepository verificationTokenRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    Logger logger;


    // Setters
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Autowired
    public void setVerificationTokenRepository(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }



    // Methods
    public void addUser(User user) {
        userRepository.save(user);
    }

    public List<User> findAll() {
        List<User> userList = userRepository.findAll();
        System.out.println(userList);
        return userList;
    }

    public boolean isUserExist(String login) {
        User user = userRepository.findUserByLogin(login);
        if (user != null) {
            return true;
        }
        return false;
    }

    public User findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    public void updateUser(User user) {
        userRepository.saveAndFlush(user);
    }

    public void updateRoles(String login, Set<Role> roleSet) {
        User user = userRepository.findUserByLogin(login);
        user.setRoleList(roleSet);
        userRepository.saveAndFlush(user);
    }

    public void deleteUser(String userLogin) {
        logger.error("deleteUser 1");
        User user = userRepository.findUserByLogin(userLogin);
        user.getRoleList().clear();
        logger.error("deleteUser 2");
//        verificationTokenRepository.delete(verificationTokenRepository.findVerificationTokenByUserLogin(userLogin));
        logger.error("deleteUser 3");
        userRepository.saveAndFlush(user);
        logger.error("deleteUser 4");
        userRepository.delete(user);
        logger.error("deleteUser 5");
    }

    public User registerNewUserAccount(UserDto userDto) {

        if (emailExist(userDto.getEmail())) {
            //TODO message or do in validator
            System.out.println("There is an account with that email address: " + userDto.getEmail());
            return null;
        } else {
        User user = new User();
//        Role role = roleRepository.findRoleByName("ROLE_USER");
        user.setLogin(userDto.getLogin());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.addToRole(roleRepository.getOne(1));
        userRepository.save(user);
//        roleRepository.save(role);
        return user;
        }
    }


    private boolean emailExist(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }


    public void createVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(token, user);
        verificationTokenRepository.save(verificationToken);
    }


    public VerificationToken getVerificationToken(String token) {
        return verificationTokenRepository.findVerificationTokenByToken(token);
    }

}
