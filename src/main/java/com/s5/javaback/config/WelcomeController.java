package com.s5.javaback.config;

import com.google.firebase.auth.UserRecord;
import com.s5.javaback.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
@RequiredArgsConstructor
public class WelcomeController {
    private final UserMapper mapper;

    @GetMapping
    public ResponseEntity<Object> welcome() {
        final var user = SecurityContextHolder.getContext().getAuthentication().getDetails();
        return ResponseEntity.ok(mapper.toFirebase((UserRecord) user));
    }

}
