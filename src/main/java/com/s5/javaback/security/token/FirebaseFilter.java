package com.s5.javaback.security.token;

import com.s5.javaback.security.model.TokenModel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FirebaseFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        try {
            final var token = getToken(req);

            if ( token != null ) {
                SecurityContextHolder.getContext().setAuthentication(new TokenModel(token));
            }
        } catch (Exception ignored) { }

        chain.doFilter(req, res);
    }

    private String getToken(HttpServletRequest req){
        final var header = req.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            return header.replace("Bearer ", "");
        }

        return null;
    }

}
