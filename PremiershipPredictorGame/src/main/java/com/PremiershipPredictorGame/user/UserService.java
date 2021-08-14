package com.PremiershipPredictorGame.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }


    public void addNewUser(User user) {
        Optional<User> userByUsername = userRepository
                .findUserByUsername(user.getUsername());
        if(userByUsername.isPresent()){
            throw new IllegalStateException("username taken");
        }
        userRepository.save(user);

    }
    public void deleteUser(Long userId){
        boolean exists = userRepository.existsById(userId);
        if(!exists){
            throw new IllegalStateException("user with id" + userId +"does not exist");
        }
        userRepository.deleteById(userId);
    }
    @Transactional
    public void updateUserPassword(Long userId,
                           String password) {
        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalStateException("user with id" + userId +"does not exist"));
        if(password!=null && password.length() > 0 && !Objects.equals(user.getPassword(), password )){
            user.setPassword(password);


        }
    }
    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(()->new IllegalStateException("User with Id"+userId+"does not exist"));
    }
}
