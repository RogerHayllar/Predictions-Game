package com.PremiershipPredictorGame.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
    private RegistrationService registrationService;
    @PostMapping
    public void register(@RequestBody RegistrationRequest request) {
        registrationService.register(request);
    }
}
