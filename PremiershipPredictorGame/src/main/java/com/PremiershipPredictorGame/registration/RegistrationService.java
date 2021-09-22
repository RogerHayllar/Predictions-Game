package com.PremiershipPredictorGame.registration;

import com.PremiershipPredictorGame.user.User;
import com.PremiershipPredictorGame.user.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

@Service
public class RegistrationService {
    private final UserRepository userRepository;


    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
        public void register(RegistrationRequest request) {

        User usertest = userRepository
                .findByUsername(request.getUsername()).orElse(null);
        if(usertest!=null){
            throw new IllegalStateException("username taken");
        }

        User user = new User(request.getUsername(), request.getPassword());
        userRepository.save(user);



    }
}
