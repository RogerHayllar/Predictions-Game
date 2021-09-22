package com.PremiershipPredictorGame.userPrediction;

import com.PremiershipPredictorGame.login.Token;
import com.PremiershipPredictorGame.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/userprediction")
public class UserPredictionController {
    private final UserPredictionService userPredictionService;
    private final UserService userService;

    @Autowired
    public UserPredictionController(UserPredictionService userPredictionService, UserService userService) {
        this.userPredictionService = userPredictionService;
        this.userService = userService;
    }

    @PostMapping(path = "/predict")
    public void addNewPrediction(@RequestBody PredictionRequest submittedPrediction,
                                 @RequestHeader("username") String username,
                                 @RequestHeader("password") String password){
        System.out.println(submittedPrediction);
        Token token = new Token(username, password);
        UserPrediction prediction = userPredictionService.createNewPrediction(submittedPrediction, token);
            userPredictionService.addPredictionToUserAndFixture(prediction);



    }

    @PostMapping(path = "admin/awardPoints/{week}")
    public void awardPointsForWeeksFixtures(@RequestHeader("username") String username,
                                      @RequestHeader("password") String password,
                                      @PathVariable("week") Integer week){
        Boolean isAdmin = userService.verifyAdminToken(username, password);
        if(isAdmin){
            userPredictionService.awardPointForFixture(week);
        }
    }
    @DeleteMapping(path = "/deletePrediction/{fixtureId}/{username}")
    public void deletePrediction(@PathVariable("fixtureId") Long fixtureID,
                                 @PathVariable("username") String username,
                                 @RequestHeader("password") String password){
        userPredictionService.deletePrediction(fixtureID,username,password);
    }
    @GetMapping(path = "/existingPredictionCheck/{fixtureId}/{username}")
    public Boolean checkIfPredictionHasBeenMade(@PathVariable("fixtureId") Long fixtureID,
                                                @PathVariable("username") String username){
        return userPredictionService.checkIfPredictionHasBeenMade(fixtureID, username);
    }
    @GetMapping(path = "/{username}/predictions/{week}")
    public List<PredictionWithFixture> getUsersPredictionsByWeek(@PathVariable("username")String username,
                                                          @PathVariable("week")Integer week){
        return userPredictionService.getUsersPredictionsByWeek(username,week);
    }

}
