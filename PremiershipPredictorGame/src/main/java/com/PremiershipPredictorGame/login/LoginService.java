package com.PremiershipPredictorGame.login;

import com.PremiershipPredictorGame.user.User;
import com.PremiershipPredictorGame.user.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public boolean loginUser(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
        .orElseThrow(()->new IllegalStateException("User does not exist"));
        if(loginRequest.getPassword().equals(user.getPassword())){
            return true;
        }
        return false;
    }
}
