package com.PremiershipPredictorGame.user;

import com.PremiershipPredictorGame.league.League;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin
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
    @GetMapping(path = "/{username}/score")
    public Integer getScoreByUsername(@PathVariable("username") String username){
        return userService.getScoreByUsername(username);

    }
    @PostMapping
    public void registerNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }


    @PutMapping(path="{username}")
    public void updateUserPassword(@PathVariable("username") String username,
                           @RequestParam String passwordNew, @RequestParam String passwordOld){
        userService.updateUserPassword(username, passwordNew, passwordOld);
    }

    @GetMapping(path = "{username}")
    public User getUserByUsername(@PathVariable("username") String username){
        return userService.getUserByUsername(username);
    }

    @GetMapping(path = "/verifyAdmin")
    public Boolean verifyAdmin(@RequestHeader(value = "username")String username,
                               @RequestHeader(value = "password")String password){
        return userService.verifyAdminToken(username,password);
    }

    @GetMapping(path = "{username}/leagues")
    public List<String> getLeaguesByUsername(@PathVariable("username") String username){
        return userService.getLeaguesByUsername(username);
    }
    @PostMapping(path = "/admin/add50points")
    public void add300pointsToUsers(@RequestHeader(value="username")String username,
                                   @RequestHeader(value="password")String password){
        userService.add300PointsToUsers(username, password);
    }

}