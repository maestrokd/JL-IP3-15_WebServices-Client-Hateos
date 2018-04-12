package com.infoPulse.lessons.model.service;

import com.infoPulse.lessons.model.entity.VerificationToken;
import com.infoPulse.lessons.model.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    // Fields
    private VerificationTokenRepository verificationTokenRepository;


    // Setters
    @Autowired
    public void setVerificationTokenRepository(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }


    // Methods
    public void deleteVerificationToken(VerificationToken verificationToken) {
        verificationTokenRepository.delete(verificationToken);
    }
}
