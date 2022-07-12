package com.fsse2203.project_backend.util;

import com.google.firebase.auth.FirebaseToken;
import org.springframework.security.core.Authentication;

public class SecurityUtil {
    public static String getUidfromFirebase(Authentication authentication){
        // Get the firebase when the access token is verified
        FirebaseToken token = (FirebaseToken)authentication.getPrincipal();
        return token.getUid();
    }
}
