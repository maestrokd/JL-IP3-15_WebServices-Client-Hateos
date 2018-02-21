package com.infoPulse.lessons.model.repository;


import com.infoPulse.lessons.model.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {

    VerificationToken findVerificationTokenByToken(String token);

    VerificationToken findVerificationTokenByUserLogin(String login);

}



