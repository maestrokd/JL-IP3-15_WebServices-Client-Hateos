package com.infoPulse.lessons.model.service;

import com.infoPulse.lessons.model.dto.UserDto;
import com.infoPulse.lessons.model.entity.Role;
import com.infoPulse.lessons.model.entity.User;
import com.infoPulse.lessons.model.entity.VerificationToken;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> findAll();
    User findUserByLogin(String login);

    boolean isUserExist(String login);
    void updateUser(User user);
    void updateRoles(String login, Set<Role> roleSet);

    void deleteUser(String userLogin);

    User registerNewUserAccount(UserDto userDto);

    void createVerificationToken(User user, String token);
    VerificationToken getVerificationToken(String token);

}
