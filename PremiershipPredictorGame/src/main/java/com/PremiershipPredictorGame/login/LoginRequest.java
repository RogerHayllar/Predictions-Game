package com.PremiershipPredictorGame.login;
import lombok.*;


@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LoginRequest {
    private final String username;
    private final String password;

}
