package com.PremiershipPredictorGame.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }
    @PostMapping
    public void registerNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }
    @DeleteMapping(path="{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);

    }
    @PutMapping(path="{userId}")
    public void updateUserPassword(@PathVariable("userId") Long userId,
                           @RequestParam String password){
        userService.updateUserPassword(userId, password);
    }
    @GetMapping(path = "{userId}")
    public User getUserById(@PathVariable("userId") Long userId){
        return userService.getUserById(userId);
    }

}