package com.s5.javaback.security.token;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.s5.javaback.security.model.TokenModel;
import com.s5.javaback.security.model.UserModel;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class FirebaseProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            final var token = (TokenModel)authentication;
            final var firebaseToken = FirebaseAuth.getInstance().verifyIdToken(token.getToken(), true);

            final var uid = firebaseToken.getUid();
            final var userRecord = FirebaseAuth.getInstance().getUser(uid);

            return new UserModel(userRecord);
        } catch (FirebaseAuthException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(TokenModel.class);
    }

}
