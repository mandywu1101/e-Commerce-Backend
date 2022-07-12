package com.fsse2203.project_backend.api;

import com.google.firebase.auth.FirebaseToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApi {
    @PostMapping("/user/login")
    public String login(Authentication authentication){
        // Get the firebase when the access token is verified
        FirebaseToken token = (FirebaseToken)authentication.getPrincipal();
        return token.getUid();
    }
}

