package com.s5.javaback.security.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;

@Getter
public class TokenModel extends AbstractAuthenticationToken {
    private final String token;

    public TokenModel(final String token) {
        super(null);
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
